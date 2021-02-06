package com.wangjie;

import org.junit.Test;
import org.mockito.verification.VerificationMode;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 2021/2/7 WangJie
 */
public class MockitoTest {
	private List mockedList = mock(List.class);
	private List mockedLinkedList = mock(LinkedList.class);

	@Test
	public void testVerifyBehavior() {
		mockedList.add("one");
		mockedList.clear();

		// 验证方法调用
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

	@Test
	public void testStubbing() {
		// mock 返回内容
		when(mockedLinkedList.get(0)).thenReturn("first");
		when(mockedLinkedList.get(1)).thenReturn(new RuntimeException());

		System.out.println(mockedLinkedList.get(0));
		// mock出了异常,但程序依然可以向下执行
		System.out.println(mockedLinkedList.get(1));
		System.out.println(mockedLinkedList.get(999));

		verify(mockedLinkedList).get(0);
	}

	@Test
	public void testVerifyTimesOfInvocations() {
		mockedList.add("once");
		mockedList.add("twice");
		mockedList.add("twice");
		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		verify(mockedList).add("once");

		// 验证方法调用次数
		verify(mockedList, times(1)).add("once");
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		verify(mockedList, never()).add("never happened");

		verify(mockedList, atMostOnce()).add("once");
		verify(mockedList, atLeastOnce()).add("three times");

		verify(mockedList, atLeast(2)).add("three times");
		verify(mockedList, atMost(5)).add("three times");
	}

	@Test
	public void testStubbingExceptions() {
		// 返回异常
		doThrow(new RuntimeException()).when(mockedList).clear();

		mockedList.clear();
	}

	// 7 deprecated

	@Test
	public void testRedundantInvocations() {
		mockedList.add("one");
		mockedList.add("two");

		verify(mockedList).add("one");
		// 验证无更多调用
		verifyNoMoreInteractions(mockedList);
	}

}

















