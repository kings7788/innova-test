package com.example.demo.service;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

	public boolean checkPass(String pwd) {

		if (StringUtils.isBlank(pwd)) {
			// 是否為空值
			System.out.println(pwd + " - Not Blank");
			return false;
		}
		// 先檢核內容(只有能小寫字母或數字，且至少各一)及長度(介於5-12)，若通過再檢核是否有序列化字母
		return checkValidLenAndContext(pwd) ? checkValidSequence(pwd) : false;
	}

	private boolean checkValidLenAndContext(String pwd) {
		// 僅有數字或小寫英文及長度介於5-12
		Pattern patternAll = Pattern.compile("^[a-z0-9]{5,12}$");

		// 是否僅有數字及長度介於5-12
		Pattern patternNum = Pattern.compile("\\d");
		Pattern patternChar = Pattern.compile("[a-z]");

		if (!patternAll.matcher(pwd).matches()) {
			System.out.println(pwd + " - Not Matcher");
			return false;
		} else if ((!patternNum.matcher(pwd).find() || (!patternChar.matcher(pwd).find()))) {
			// 是否僅有數字及長度介於5-12
			System.out.println(pwd + " - Not least one Matcher");
			return false;

		}
		return true;
	}

	private boolean checkValidSequence(String pwd) {
		int maxLen = pwd.getBytes().length - 1;
		System.out.println(maxLen);
		byte[] byteArr = pwd.getBytes();
		for (int len = 0; len < maxLen; len++) {
			int compareRes = (byteArr[len] - byteArr[len + 1]);
			// compareRes若為0表示字元相同，1 or -1表示字為連續相關
			if (compareRes == 0 || compareRes == -1 || compareRes == 1) {
				System.out.println(pwd + " - Not Matcher Sequence");
				return false;
			}
		}
		System.out.println(pwd + " - Pass ValidSequence Check");
		return true;
	}
}
