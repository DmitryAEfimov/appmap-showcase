package ru.defimov.showcase.appmap.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonDto implements Serializable {
	private long id;
	private String name;
}
