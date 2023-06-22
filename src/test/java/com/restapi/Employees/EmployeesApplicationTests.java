package com.restapi.Employees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeesApplicationTests {

	@Test
	public void testMain() {
		String test = "main test";
		EmployeesApplication.main(new String[]{"args"});
		Assertions.assertNotNull(test);
	}

}
