package auto.utilities.fakedata

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class VNName extends DataFaker{

	public VNName() {
		super();
	}

	public String surName() {
		return VN_SUR_NAME[faker.random().nextInt(VN_SUR_NAME.size())]
	}

	public String middleName() {
		return VN_MIDDLE_NAME[faker.random().nextInt(VN_MIDDLE_NAME.size())]
	}

	public String givenName() {
		return VN_GIVEN_NAME[faker.random().nextInt(VN_GIVEN_NAME.size())]
	}

	public String fullName() {
		return "${surName()} ${middleName()} ${givenName()}"
	}


	private final List<String> VN_GIVEN_NAME = [
		"Mạnh",
		"Triết",
		"Minh",
		"Nam",
		"Tài",
		"Kiên",
		"Hưng",
		"Khôi",
		"Thịnh",
		"Đạt",
		"Hùng",
		"Tâm",
		"An",
		"Dũng",
		"Thắng",
		"Nghĩa",
		"Bảo",
		"Lâm",
		"Long",
		"Phong",
		"Giang",
		"Vân",
		"Thủy",
		"Hoài",
		"Trang",
		"Chang",
		"Giang",
		"Phú",
		"Thái",
		"Nhi",
		"Như",
		"Nga",
		"Dương",
		"Ngọc",
		"Ngà",
		"Nhung",
		"Huyền",
		"Linh",
		"Toàn",
		"Hoa"
	];


	private final List<String> VN_MIDDLE_NAME = [
		"Quý",
		"Vĩnh",
		"Đức",
		"Trung",
		"Năng",
		"Quốc",
		"Gia",
		"Thế",
		"Thị",
		"Thu",
		"Huyền",
		"Trọng",
		"Văn",
		"Hồng",
		"Xuân",
		"Bá",
		"Đình",
		"Kim",
		"Lệ",
		"Quang",
		"Chí",
		"Thanh",
		"Tuyết"
	];

	private final List<String> VN_SUR_NAME = [
		"Nguyễn",
		"Trần",
		"Lê",
		"Phạm",
		"Hoàng",
		"Vũ",
		"Phan",
		"Trương",
		"Bùi",
		"Đặng",
		"Đỗ",
		"Ngô",
		"Hồ",
		"Dương",
		"Đinh",
		"Diêm",
		"Âu Dương",
		"Lý",
		"Ninh",
		"Lại",
		"Kiều",
		"Tạ",
		"Tô",
		"Thượng Quan",
		"Gia Cát",
		"Hạ",
		"Đoàn",
		"Chu",
		"Diệp",
		"Tần"
	];
}
