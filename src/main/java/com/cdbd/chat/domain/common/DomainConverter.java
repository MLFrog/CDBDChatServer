package com.cdbd.chat.domain.common;

public interface DomainConverter<A, B> {
	B convert(A a);
}