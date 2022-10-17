package com.example.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.service.CheckService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
class TestApplicationTests {

	@Autowired
	CheckService testSrevice;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkPwdTest() {
		Assert.assertEquals(false, testSrevice.checkPass("")); // 空值
		Assert.assertEquals(false, testSrevice.checkPass(" ")); // 空白值(長度不到)
		Assert.assertEquals(false, testSrevice.checkPass("        ")); // 空白值(長度為5-12)
		Assert.assertEquals(false, testSrevice.checkPass("dzsdsg")); // 只有小寫英文
		Assert.assertEquals(false, testSrevice.checkPass("713216")); // 只有數字
		Assert.assertEquals(false, testSrevice.checkPass("A!@#14")); // 包含非小寫英文及數字文字
		Assert.assertEquals(false, testSrevice.checkPass("這是測試喔")); // 包含非小寫英文及數字文字(中文)
		Assert.assertEquals(false, testSrevice.checkPass("bau71測")); // 包含非小寫英文及數字文字(中文)
		Assert.assertEquals(false, testSrevice.checkPass("DgSsdsg151"));// 包含非小寫英文及數字文字(大寫英文)
		Assert.assertEquals(false, testSrevice.checkPass("ano7")); // 符合原則但長度過短(<5)
		Assert.assertEquals(false, testSrevice.checkPass("ano7qwrera4813")); // 符合原則但長度過長(>12)
		Assert.assertEquals(false, testSrevice.checkPass("ano78")); // 符合原則但含有序列化數字(78)
		Assert.assertEquals(false, testSrevice.checkPass("ano87")); // 符合原則但含有序列化數字(87)
		Assert.assertEquals(false, testSrevice.checkPass("ano77")); // 符合原則但含有序列化相同數字(77)
		Assert.assertEquals(false, testSrevice.checkPass("abc791")); // 符合原則但含有序列化字母(abc)
		Assert.assertEquals(false, testSrevice.checkPass("aac715")); // 符合原則但含有序列化相同字母(aa)
		Assert.assertEquals(false, testSrevice.checkPass("bau708")); // 符合原則但含有序列化字母(ba)

		Assert.assertEquals(true, testSrevice.checkPass("dgsdsg151"));// 符合原則
		Assert.assertEquals(true, testSrevice.checkPass("151hobu"));// 符合原則
		Assert.assertEquals(true, testSrevice.checkPass("boen1915"));// 符合原則
	}
}
