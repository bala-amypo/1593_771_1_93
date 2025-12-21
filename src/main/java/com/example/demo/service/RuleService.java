package com.example.demo2.service;

import java.util.List;

import com.example.demo2.model.InteractionRule;

public interface RuleService {

    InteractionRule addRule(InteractionRule rule);

    List<InteractionRule> getAllRules();
}
