package catholic.project.addressManager.address;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressMapper {


    int insert_addr(List<Map<String, Object>> list);

    int toInsert(@Param("pk") String pk);
    int insert_bubJung(List<Map<String, Object>> list);



    // get 법정동코드 in 법정동관리m
    List<String> getBubCodeListM();

    // get 법정동코드 in 법정동관리test
    List<String> getBubCodeListTest();

    // 법정동관리m의 데이터를 가져온다.
    List<BubVO> selectByList(List<String> codeList);

    // 법정동관리test에 없는 데이터를 넣어준다.
    void insertBubJungCodes(List<BubVO> list);




    // get 건물관리번호 from 도로명주소N
    List<String> getDoroCodeListN();

    // get 건물관리번호 from 도로명주소Test
    List<String> getDoroCodeListTest();

    // 도로명주소n의 데이터를 가져온다.
    List<DoroVO> selectDoroListByCodeList(List<String> codeList);

    // 도로명주소test에 없는 데이터를 넣어준다.
    void insertDoroData(List<DoroVO> list);


    // 법정동 주소 일변동 insert/update/delete
    void alter_insert_bubJung(Map<String, Object> map);
    int alter_update_bubJung(Map<String, Object> map);
    void alter_delete_bubJung(Map<String, Object> map);


    // 도로명 주소 일변동 insert/update/delete
    void alter_insert_addr(Map<String, Object> map);
    int alter_update_addr(Map<String, Object> map);
    void alter_delete_addr(Map<String, Object> map);


}
