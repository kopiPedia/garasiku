package com.koped;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GarasikuApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> GarasikuApplication.main(new String[] {}));
	}

}
