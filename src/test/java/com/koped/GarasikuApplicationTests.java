package com.koped;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Assertions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(SpringExtension.class)
class GarasikuApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> GarasikuApplication.main(new String[] {}));
	}

}
