package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class wifiService {

	public List<LibDto> selectWifi(String lat, String lnt) {
		List<LibDto> wifiList = new ArrayList<>();

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

			String sql = "select *, ACOS(COS(RADIANS(90-lnt)) * COS(RADIANS(90-?)) "
					+ "+ SIN(RADIANS(90-lnt)) * SIN(RADIANS(90-?)) * COS(RADIANS(lat-?))) * 6371 as distance "
					+ "from seoul_wifi order by distance limit 20";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(lat));
			preparedStatement.setString(2, String.valueOf(lat));
			preparedStatement.setString(3, String.valueOf(lnt));

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String dis = rs.getString("DISTANCE").substring(0, 6);
				String mgr_no = rs.getString("MGR_NO");
				String wrdofc = rs.getString("WRDOFC");
				String main_nm = rs.getString("MAIN_NM");
				String adres1 = rs.getString("ADRES1");
				String adres2 = rs.getString("ADRES2");
				String instl_floor = rs.getString("INSTL_fLOOR");
				String instl_ty = rs.getString("INSTL_TY");
				String instl_mby = rs.getString("INSTL_MBY");
				String svc_se = rs.getString("SVC_SE");
				String cmcwr = rs.getString("CMCWR");
				String cnstc_year = rs.getString("CNSTC_YEAR");
				String inout_door = rs.getString("INOUT_DOOR");
				String remars3 = rs.getString("REMARS3");
				String lnt1 = rs.getString("LNT");
				String lat1 = rs.getString("LAT");
				String work_dttm = rs.getString("WORK_DTTM");

				LibDto libdto = new LibDto();

				libdto.setDistance(dis);
				libdto.setMgr_no(mgr_no);
				libdto.setWrdofc(wrdofc);
				libdto.setMain_nm(main_nm);
				libdto.setAdres1(adres1);
				libdto.setAdres2(adres2);
				libdto.setInstl_floor(instl_floor);
				libdto.setInstl_ty(instl_ty);
				libdto.setInstl_mby(instl_mby);
				libdto.setSvc_se(svc_se);
				libdto.setCmcwr(cmcwr);
				libdto.setCnstc_year(cnstc_year);
				libdto.setInout_door(inout_door);
				libdto.setRemars3(remars3);
				libdto.setLnt(lnt1);
				libdto.setLat(lat1);
				libdto.setWork_dttm(work_dttm);

				wifiList.add(libdto);
			}

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
		return wifiList;
	}

	public LibDto selectDtail(String mgr_no, String main_nm) {
		LibDto libdto = null;

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

			String sql = "select * from seoul_wifi where mgr_no = ? and main_nm = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(mgr_no));
			preparedStatement.setString(2, String.valueOf(main_nm));

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				libdto = new LibDto();

				libdto.setMgr_no(rs.getString("mgr_No"));
				libdto.setWrdofc(rs.getString("wrdofc"));
				libdto.setMain_nm(rs.getString("main_nm"));
				libdto.setAdres1(rs.getString("adres1"));
				libdto.setAdres2(rs.getString("adres2"));
				libdto.setInstl_floor(rs.getString("instl_floor"));
				libdto.setInstl_ty(rs.getString("instl_ty"));
				libdto.setInstl_mby(rs.getString("instl_mby"));
				libdto.setSvc_se(rs.getString("svc_se"));
				libdto.setCmcwr(rs.getString("cmcwr"));
				libdto.setCnstc_year(rs.getString("cnstc_year"));
				libdto.setInout_door(rs.getString("inout_door"));
				libdto.setRemars3(rs.getString("remars3"));
				libdto.setLat(rs.getString("lat"));
				libdto.setLnt(rs.getString("lnt"));
				libdto.setWork_dttm(rs.getString("work_dttm"));
			}

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
		return libdto;
	}

	public void insertBookmarkGroup(String name, int turn) {
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

			String sql = "insert into bookmark_group(name, turn) values (?, ?)";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(name));
			preparedStatement.setInt(2, turn);

			rs = preparedStatement.executeQuery();

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

	public List<LibDto> selectBookmarkGroup() {
		List<LibDto> bgList = new ArrayList<>();

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

			String sql = "select * from bookmark_group";

			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int turn = rs.getInt("turn");
				String registration_date = rs.getString("registration_date");
				String modification_date = rs.getString("modification_date");

				LibDto libdto = new LibDto();

				libdto.setId(id);
				libdto.setName(name);
				libdto.setTurn(turn);
				libdto.setRegistration_date(registration_date);
				libdto.setModification_date(modification_date);

				bgList.add(libdto);
			}

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
		return bgList;
	}

	public void updateBookmarkGroup(String oldName, int oldTurn, String newName, int newTurn) {
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

			String sql = "update bookmark_group set name = ?, turn = ? , modification_date = now() where name = ? and turn = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, newTurn);
			preparedStatement.setString(3, oldName);
			preparedStatement.setInt(4, oldTurn);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}

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

	public void deleteBookmarkGroup(String name, int turn) {
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

			String sql = "delete from bookmark_group where name = ? and turn = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, turn);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}

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

	public void insertBookmark(String selectedValue, String main_nm) {
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

			String sql = "insert into bookmark_list select b.id, b.name, sw.main_nm, b.registration_date "
					+ "from seoul_wifi sw join bookmark_group b where sw.main_nm = ? and b.name = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, main_nm);
			preparedStatement.setString(2, selectedValue);

			rs = preparedStatement.executeQuery();

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

	public List<LibDto> selectBookmark() {
		List<LibDto> selbookList = new ArrayList<>();

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

			String sql = "select * from bookmark_list ";

			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String main_nm = rs.getString("main_nm");
				String registration_date = rs.getString("registration_date");

				LibDto libdto = new LibDto();

				libdto.setId(id);
				libdto.setName(name);
				libdto.setMain_nm(main_nm);
				libdto.setRegistration_date(registration_date);

				selbookList.add(libdto);
			}

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
		return selbookList;
	}

	public void deleteBookmark(int id) {
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

			String sql = "delete from bookmark_list where id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}

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

	public void insertHistory(String lnt, String lat) {
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

			String sql = "insert into history(lnt, lat) values (?, ?);";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(lnt));
			preparedStatement.setString(2, String.valueOf(lat));

			rs = preparedStatement.executeQuery();

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

	public List<LibDto> selectHistory() {
		List<LibDto> hisList = new ArrayList<>();

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

			String sql = "select * from history";

			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String lnt = rs.getString("lnt");
				String lat = rs.getString("lat");
				String lookup_date = rs.getString("lookup_date");

				LibDto libdto = new LibDto();

				libdto.setId(id);
				libdto.setLnt(lnt);
				libdto.setLat(lat);
				libdto.setLookup_date(lookup_date);

				hisList.add(libdto);

			}

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

		return hisList;
	}

	public void deleteHistory(int id) {
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

			String sql = "delete from history where id = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}

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