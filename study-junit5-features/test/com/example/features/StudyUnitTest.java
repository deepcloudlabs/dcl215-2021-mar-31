package com.example.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.domain.Account;

// CUT : Class Under Test -> Account
public class StudyUnitTest {
   // MUT: Method Under Test -> withdraw
   @Test
   @DisplayName("Customer withdraws all amount with success")
   public void test1() {
	   // 1. Test Fixture/Setup
	   var acc = new Account("tr1", 10_000);
	   // 2. Call exercise method
	   var success = acc.withdraw(10_000);
	   // 3. Verification
	   assertTrue(success);
	   assertEquals(0.0, acc.getBalance());
	   // 4. Tear-down: destroys/release all resources created/allocated in step #1
	   // noop
   }
}
