package pl.dawidbronczak.spring.WhatICanEat.exception;

public class ResourceAlreadyExistException extends RuntimeException {
	public ResourceAlreadyExistException(Class<?> resourceClass, String name) {
		super(resourceClass.getSimpleName()+" with name: "+name+" alredy exist");
	}
}
