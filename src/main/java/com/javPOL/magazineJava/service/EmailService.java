package com.javPOL.magazineJava.service;

public interface EmailService{

    void sendSimpleMessage(String to, String subject, String text);
}