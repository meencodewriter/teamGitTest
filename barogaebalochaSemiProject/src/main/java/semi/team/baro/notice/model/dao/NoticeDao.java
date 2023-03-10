package semi.team.baro.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import semi.team.baro.notice.model.vo.Notice;

public class NoticeDao {

	public int insertNotice(Connection connection, Notice notice) {
		PreparedStatement preparedStatement = null;
		String query = "insert into notice values(notice_seq.nextval,?,?,?,?,0,(TO_CHAR(SYSDATE,'YYYY-MM-DD/HH24:MI:SS')))";
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, notice.getMemberNo());
			preparedStatement.setString(2, notice.getNoticeCategory());
			preparedStatement.setString(3, notice.getNoticeTitle());
			preparedStatement.setString(4, notice.getNoticeContent());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
		}
		return result;
	}
	public ArrayList<Notice> selectAllNoticeList(Connection connection) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select notice_no, member_id, notice_category, notice_title, notice_content, read_count, reg_date from notice join member_tbl using(member_no) order by 1 desc";
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int noticeNo = resultSet.getInt(1);
				String memberId = resultSet.getString(2);
				String noticeCategory = resultSet.getString(3);
				String noticeTitle = resultSet.getString(4);
				String noticeContent = resultSet.getString(5);
				int readCount = resultSet.getInt(6);
				String regDate = resultSet.getString(7);
				Notice notice = new Notice(noticeNo, noticeCategory, noticeTitle, noticeContent, readCount, regDate, memberId);
				noticeList.add(notice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
			JDBCTemplate.close(resultSet);
		}
		return noticeList;
	}
	public ArrayList<Notice> selectNoticeList(Connection connection, int start, int end) {
		String query = "SELECT * FROM (SELECT ROWNUM AS LIST_COUNT, ALLLIST.* FROM (select notice_no, member_id, notice_category, notice_title, notice_content, read_count, reg_date from notice join member_tbl using(member_no) order by 1 desc) ALLLIST) WHERE LIST_COUNT BETWEEN ? AND ?";
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int noticeNo = resultSet.getInt("notice_no");
				String memberId = resultSet.getString("member_Id");
				String noticeCategory = resultSet.getString("notice_category");
				String noticeTitle = resultSet.getString("notice_title");
				String noticeContent = resultSet.getString("notice_content");
				int readCount = resultSet.getInt("read_count");
				String regDate = resultSet.getString("reg_date");
				Notice notice = new Notice(noticeNo, noticeCategory, noticeTitle, noticeContent, readCount, regDate, memberId);
				noticeList.add(notice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
			JDBCTemplate.close(resultSet);
		}
		return noticeList;
	}
	public int selectNoticeCount(Connection connection) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String quert = "select count(*) as total_count from notice";
		int totalCount = 0;
		try {
			preparedStatement = connection.prepareStatement(quert);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total_count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
			JDBCTemplate.close(resultSet);
		}
		return totalCount;
	}
	public Notice selectOneNotice(Connection connection, int noticeNo) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from notice where notice_no = ?";
		Notice notice = new Notice();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, noticeNo);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				notice.setNoticeCategory(resultSet.getString("notice_category"));
				notice.setNoticeContent(resultSet.getString("notice_content"));
				notice.setNoticeNo(resultSet.getInt("notice_no"));
				notice.setNoticeTitle(resultSet.getString("notice_title"));
				notice.setReadCount(resultSet.getInt("read_count"));
				notice.setRegDate(resultSet.getString("reg_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
			JDBCTemplate.close(resultSet);
		}
		return notice;
	}
	public String getNoticeWriter(Connection connection, int noticeNo) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select member_id from notice join member_tbl using(member_no) where notice_no = ?";
		String memberId = "";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, noticeNo);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				memberId = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
			JDBCTemplate.close(resultSet);
		}
		return memberId;
	}
	public int updateReadCount(Connection connection, int noticeNo) {
		PreparedStatement preparedStatement = null;
		String query = "update notice set read_count = (read_count+ 1) where notice_no = ?";
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, noticeNo);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
		}
		return result;
	}
	public int removeNotice(Connection connection, int noticeNo) {
		PreparedStatement preparedStatement = null;
		String query = "delete from notice where notice_no = ?";
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, noticeNo);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
		}
		return result;
	}
	public int noticeUpdate(Connection connection, Notice notice) {
		PreparedStatement preparedStatement = null;
		String query = "update notice set notice_category = ?, notice_title = ? , notice_content = ? where notice_no = ?";
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, notice.getNoticeCategory());
			preparedStatement.setString(2, notice.getNoticeTitle());
			preparedStatement.setString(3, notice.getNoticeContent());
			preparedStatement.setInt(4, notice.getNoticeNo());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(preparedStatement);
		}
		return result;
	}
}
