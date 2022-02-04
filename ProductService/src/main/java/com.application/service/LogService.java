package com.application.service;

public interface LogService {
    public void logTemplate(String message, String logType, Class fromClass);
    public void sendMessage(String message);
}
