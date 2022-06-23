package com.nerdket.market.app.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.app.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;
}
