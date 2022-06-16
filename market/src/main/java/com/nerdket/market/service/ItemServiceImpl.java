package com.nerdket.market.service;

import org.springframework.stereotype.Service;

import com.nerdket.market.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;
}
