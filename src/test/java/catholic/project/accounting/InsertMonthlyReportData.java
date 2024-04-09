package catholic.project.accounting;


import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertMonthlyReportData {

    public DataSource dataSource() {

        // BasicDataSource : added dbcp dependency
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        // 개발
         dataSource.setUrl("jdbc:postgresql://172.24.8.19:5432/church");
         dataSource.setUsername("bys_test_adm");
         dataSource.setPassword("bys_test_adm#$");

        // 운영
//        dataSource.setUrl("jdbc:postgresql://172.24.8.11:5432/church");
//        dataSource.setUsername("bys_prd_adm");
//        dataSource.setPassword("bys_prd_adm12#");


        return dataSource;
    }

    // * DAO -> JDBC -> Database
    // Original JDBC - Connection 객체 필요
    // Spring JDBC - DataSource 필요.
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    private void insertMonthlyReportData(String yearMonth, String yearMonthDate, String yearMonthDate2){
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        String sql = "insert into 회계_월말보고서내역 " +
                "(교구번호,본당번호,기준년월,등록여부,등록일자,관리자확인여부,관리자확인일자,확인자성명,프로그램id,배치처리일시,최초입력사용자id,최초입력일시,최종변경사용자id,최종변경일시) " +
                " VALUES ('24','24078', ? ,'1', ? ,'1', ? ,'','',NULL,'admin',now(),'admin',now())";

        jdbcTemplate.update(sql, yearMonth, yearMonthDate, yearMonthDate2);
    }

    @Test
    void 월말보고서내역데이터추가() {

        int from = 2020;
        int to = 2022;

        HashMap<Integer, List<TestVO>> map = new HashMap<Integer, List<TestVO>>();

        for (int year = from; year <= to; year++) {

            List list = new ArrayList<TestVO>();

            for (int month = 1; month <= 12; month++) {

                TestVO vo = new TestVO();


                String parameter1 = String.format("%04d%02d", year, month);
                String parameter2 = String.format("%04d%02d01", year, month);

                vo.setYearMonth(parameter1);
                vo.setYearMonthDate(parameter2);

                list.add(vo);
            }

            map.put(year, list);

        }


        for (int year = from; year <= to; year++) {
            for (int i = 0; i < map.get(year).size(); i++) {

                String yearMonth = map.get(year).get(i).getYearMonth();
                String yearMonthDate = map.get(year).get(i).getYearMonthDate();

                insertMonthlyReportData(yearMonth, yearMonthDate, yearMonthDate);
            }
        }

    }

}
