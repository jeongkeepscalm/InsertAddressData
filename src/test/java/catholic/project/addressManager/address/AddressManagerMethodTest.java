package catholic.project.addressManager.address;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest // 매소드 테스트
public class AddressManagerMethodTest {

    @Autowired
    AddressManager addressManager;

    @Autowired
    AddressService addressService;

    @Autowired
    AlterAddressService alterAddressService;


    @Test
    void 압축풀기() throws Exception {
        addressManager.extractZip();
    }

    @Test
    void 도로명파일읽고데이터저장() {
        addressManager.readFileAndDBProcess();
    }

    @Test
    void 법정동파일읽고데이터저장() throws Exception {
        addressManager.readFileAndDBProcess2();
    }


    @Test
    void 법정동관리test에_없는_데이터를_넣어준다(){

        // 공통법정동관리m 조회
        List<String> bubCodeListM = addressService.getBubCodeListM();

        // 공통법정동관리test 조회
        List<String> bubCodeListTest = addressService.getBubCodeListTest();

        // 비교 후 test에 없는 데이터 리스트 반환
        List<String> codeListToAdd = addressManager.compareLists(bubCodeListM, bubCodeListTest);

        if (codeListToAdd.size() == 0) {
            System.out.println("추가할 데이터 없음");
            return;
        }

        // 공통법정동관리n 에서 조회
        List<BubVO> bubVOList = addressService.selectByList(codeListToAdd);

        // 공통법정동관리test에 없는 데이터를 넣어준다.
        addressService.insertBubJungCodes(bubVOList);

    }

    @Test
    void 도로명주소test에_없는_데이터를_넣어준다(){

        // 도로명주소n 조회
        List<String> getDoroCodeListN = addressService.getDoroCodeListN();

        // 도로명주소test 조회
        List<String> getDoroCodeListTest = addressService.getDoroCodeListTest();

        // 비교 후 test에 없는 데이터 리스트 반환
        List<String> codeListToAdd = addressManager.compareLists(getDoroCodeListN, getDoroCodeListTest);

        if (codeListToAdd.size() == 0) {
            System.out.println("추가할 데이터 없음");
            return;
        }

        // 도로명주소n 에서 조회
        List<DoroVO> doroVOList = addressService.selectDoroListByCodeList(codeListToAdd);

        // 도로명주소test에 없는 데이터를 넣어준다.
        addressService.insertDoroData(doroVOList);


    }



    @Test
    void alter_일별법정동주소() {
        alterAddressService.readFileAndDBProcess2();
    }


    @Test
    void alter_일별도로명주소() {
        alterAddressService.readFileAndDBProcess();
    }





}

