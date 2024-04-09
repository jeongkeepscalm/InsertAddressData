package catholic.project.addressManager.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper dao;

    final int SIZE_LIMIT = 1000;

    public int insert_addr(List<Map<String, Object>> list){
        return dao.insert_addr(list);
    }

    public boolean toInsert(String pk){
        return dao.toInsert(pk) == 0 ? true : false;
    }

    public int insert_bubJung(List<Map<String, Object>> list){
        return dao.insert_bubJung(list);
    }


    public List<String> getBubCodeListM() {
        System.out.println("dao.getBubCodeListM().size() : " + dao.getBubCodeListM().size());
        return dao.getBubCodeListM();
    }

    public List<String> getBubCodeListTest() {
        System.out.println("dao.getBubCodeListTest().size() : " + dao.getBubCodeListTest().size());
        return dao.getBubCodeListTest();
    }

    public List<BubVO> selectByList(List<String> codeList) {
        if (codeList.size() != 0) {
            return dao.selectByList(codeList);
        } else {
            return null;
        }

    }

    public void insertBubJungCodes(List<BubVO> list) {
        try {
            dao.insertBubJungCodes(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<String> getDoroCodeListN() {
        List<String> list = dao.getDoroCodeListN();
        System.out.println("DoroN Size : " + list.size());
        return list;
    }

    public List<String> getDoroCodeListTest() {
        List<String> list = dao.getDoroCodeListTest();
        System.out.println("DoroTest Size : " + list.size());
        return list;
    }

    public List<DoroVO> selectDoroListByCodeList(List<String> codeList) {

        // 30만이나 되는 데이터를 한 번에 조회할 수 없었다.
//        List<DoroVO> doroVOList = dao.selectDoroListByCodeList(codeList);
//        System.out.println(doroVOList);
//        return doroVOList;

        List<DoroVO> allDoroVOList = new ArrayList<>(); // 최종 결과를 담을 리스트

        // codeList를 SIZE_LIMIT 크기로 분할하여 처리
        for (int i = 0; i < codeList.size(); i += SIZE_LIMIT) {
            int end = Math.min(i + SIZE_LIMIT, codeList.size()); // 마지막 인덱스 계산
            List<String> subList = codeList.subList(i, end); // 부분 리스트 생성

            List<DoroVO> doroVOList = dao.selectDoroListByCodeList(subList);
            allDoroVOList.addAll(doroVOList);
        }
        System.out.println(allDoroVOList.get(0));
        return allDoroVOList;
    }

    public void insertDoroData(List<DoroVO> list) {

        try {

            for (int i = 0; i < list.size(); i += SIZE_LIMIT) {

                int end = Math.min(i + SIZE_LIMIT, list.size()); // 마지막 인덱스 계산
                List<DoroVO> subList = list.subList(i, end); // 부분 리스트 생성

                // 부분 리스트에 대해 데이터 삽입 시도
                dao.insertDoroData(subList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    // 법정동 주소 일변동 insert/update/delete
    public void alter_insert_bubJung(Map<String, Object> map) {
        dao.alter_insert_bubJung(map);
    }
    public int alter_update_bubJung(Map<String, Object> map) {
        return dao.alter_update_bubJung(map);
    }
    public void alter_delete_bubJung(Map<String, Object> map) {
        dao.alter_delete_bubJung(map);
    }


    // 도로명 주소 일변동 insert/update/delete
    public void alter_insert_addr(Map<String, Object> map) {
        dao.alter_insert_addr(map);
    }
    public int alter_update_addr(Map<String, Object> map) {
        return dao.alter_update_addr(map);
    }
    public void alter_delete_addr(Map<String, Object> map) {
        dao.alter_delete_addr(map);
    }



}
