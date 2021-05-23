package com.example.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserDto;

@Service
public class UserService {
	@Autowired
	private UserDto userdto;

	/** カンマ */
	private static final String COMMA = ",";
	/** 改行コード */
	private static final String NEW_LINE = "\r\n";


	public HashMap<String, ArrayList<UserDto>> getUser() throws IOException {
		FileInputStream fileInput;
		fileInput = new FileInputStream("C:\\Users\\kmvsm\\Documents\\chaosapp\\ChaosApp\\src\\main\\java\\com\\example\\demo\\service\\users.csv");
										//C:\Users\kmvsm\Documents\chaosapp\ChaosApp\src\main\java\com\example\demo\service
		InputStreamReader inputStream = new InputStreamReader(fileInput);
		BufferedReader br = new BufferedReader(inputStream);

		ArrayList<UserDto> userList = new ArrayList<UserDto>();
		String line;
		while ((line = br.readLine()) != null) {
			String[] userData = line.split(",", 0);
				userdto = new UserDto();
				userdto.setId(userData[0]);
				userdto.setName(userData[1]);
				userdto.setAge(userData[2]);
				userList.add(userdto);
		}
		br.close();

		HashMap<String, ArrayList<UserDto>> jsonUser = new HashMap<String, ArrayList<UserDto>>();
		jsonUser.put("users", userList);

		return jsonUser;
	}

	public void insertUser(String userId, String userName, String userAge) {
		FileWriter fw = null;
		try {
			File file = new File("C:\\Users\\kmvsm\\Documents\\chaosapp\\ChaosApp\\src\\main\\java\\com\\example\\demo\\service\\users.csv");
			fw = new FileWriter(file, true);

			fw.write(userId + COMMA);
			fw.write(userName + COMMA);
			fw.write(userAge + COMMA);
			fw.write(NEW_LINE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.flush();
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}