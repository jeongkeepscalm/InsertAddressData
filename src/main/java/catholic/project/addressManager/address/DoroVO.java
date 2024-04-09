package catholic.project.addressManager.address;

import lombok.Data;

@Data
public class DoroVO {

    private String 구역번호;
    private String 시도;
    private String 시도영문;
    private String 시군구;
    private String 시군구영문;
    private String 읍면;
    private String 읍면영문;
    private String 도로명코드;
    private String 도로명;
    private String 도로명영문;
    private String 지하여부;
    private int 건물번호본번;
    private int 건물번호부번;
    private String 건물관리번호; // PK
    private String 다량배달처명;
    private String 시군구용건물명;
    private String 법정동코드;
    private String 법정동명;
    private String 리;
    private String 행정동명;
    private String 산여부;
    private String 지번본번;
    private String 읍면동일련번호;
    private String 지번부번;
    private String 우편번호;
    private String 우편일련번호;
    private String 공동주택여부;
    private String 도로명주소;
    private String 지번주소;
    private String 검색용_도로명주소;
    private String 검색용_지번주소;

}
