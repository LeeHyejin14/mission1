package db;

import java.io.*;
import java.net.URL;
import java.sql.*;

import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class apiService {

	public int api() throws IOException {
		String key = "434e4c5371686a6937317761547554";
		int start = 1, end = 1000;
		int count = 0;

		try {
			do {
				// 파싱한 데이터를 저장할 변수
				String result = "";

				URL url = new URL(
						"http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/" + start + "/" + end);

				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

				result = bf.readLine();

				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
				JSONObject wifiCount = (JSONObject) jsonObject.get("TbPublicWifiInfo");
				count = Integer.valueOf(wifiCount.get("list_total_count").toString()); // 총 개수

				JSONArray row = (JSONArray) wifiCount.get("row");

				ArrayList<LibDto> list = new ArrayList<>();
				for (int j = 0; j < row.size(); j++) {
					LibDto libdto = new LibDto();
					JSONObject row1 = (JSONObject) row.get(j);

					libdto.setMgr_no(((String) row1.get("X_SWIFI_MGR_NO")).replaceAll("[-]", ""));
					libdto.setWrdofc((String) row1.get("X_SWIFI_WRDOFC"));
					libdto.setMain_nm((String) row1.get("X_SWIFI_MAIN_NM"));
					libdto.setAdres1((String) row1.get("X_SWIFI_ADRES1"));
					libdto.setAdres2((String) row1.get("X_SWIFI_ADRES2"));
					libdto.setInstl_floor((String) row1.get("X_SWIFI_INSTL_FLOOR"));
					libdto.setInstl_ty((String) row1.get("X_SWIFI_INSTL_TY"));
					libdto.setInstl_mby((String) row1.get("X_SWIFI_INSTL_MBY"));
					libdto.setSvc_se((String) row1.get("X_SWIFI_SVC_SE"));
					libdto.setCmcwr((String) row1.get("X_SWIFI_CMCWR"));
					libdto.setCnstc_year((String) row1.get("X_SWIFI_CNSTC_YEAR"));
					libdto.setInout_door((String) row1.get("X_SWIFI_INOUT_DOOR"));
					libdto.setRemars3((String) row1.get("X_SWIFI_REMARS3"));
					libdto.setLat((String) row1.get("LAT"));
					libdto.setLnt((String) row1.get("LNT"));
					libdto.setWork_dttm((String) row1.get("WORK_DTTM"));

					list.add(libdto);
				}

				register(list, row.size());

				start += 1000;
				end += 1000;
			} while (!(end > count + 1000));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public void register(ArrayList<LibDto> arrayList, int rowSize) {
		String url = "jdbc:mariadb://localhost:3306/testdb1";
		String dbUserId = "root";
		String dbPassword = "zerobase";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			connection.setAutoCommit(false);

			String sql = "insert into SEOUL_WIFI values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < rowSize; i++) {
				LibDto libdto = (LibDto) arrayList.get(i);

				preparedStatement.setString(1, libdto.getMgr_no());
				preparedStatement.setString(2, libdto.getWrdofc());
				preparedStatement.setString(3, libdto.getMain_nm());
				preparedStatement.setString(4, libdto.getAdres1());
				preparedStatement.setString(5, libdto.getAdres2());
				preparedStatement.setString(6, libdto.getInstl_floor());
				preparedStatement.setString(7, libdto.getInstl_ty());
				preparedStatement.setString(8, libdto.getInstl_mby());
				preparedStatement.setString(9, libdto.getSvc_se());
				preparedStatement.setString(10, libdto.getCmcwr());
				preparedStatement.setString(11, libdto.getCnstc_year());
				preparedStatement.setString(12, libdto.getInout_door());
				preparedStatement.setString(13, libdto.getRemars3());
				preparedStatement.setString(14, libdto.getLat());
				preparedStatement.setString(15, libdto.getLnt());
				preparedStatement.setString(16, libdto.getWork_dttm());

				int affected = preparedStatement.executeUpdate();

				if (affected > 0) {
					System.out.println("저장 성공");
				} else {
					System.out.println("저장 실패");
				}

			}
			connection.commit();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}