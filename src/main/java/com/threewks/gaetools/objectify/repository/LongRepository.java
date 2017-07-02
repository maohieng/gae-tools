/*
 * This file is a component of thundr, a software library from 3wks.
 * Read more: http://3wks.github.io/thundr/
 * Copyright (C) 2015 3wks, <thundr@3wks.com.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.threewks.gaetools.objectify.repository;

import com.atomicleopard.expressive.ETransformer;
import com.googlecode.objectify.Key;
import com.threewks.gaetools.search.gae.SearchConfig;

public class LongRepository<E> extends AbstractRepository<E, Long> {
	public LongRepository(final Class<E> entityType, SearchConfig searchConfig) {
		super(entityType, fromLong(entityType), toLong(entityType), searchConfig);
	}

	static <E> ETransformer<Long, Key<E>> fromLong(final Class<E> type) {
		return new ETransformer<Long, Key<E>>() {
			@Override
			public Key<E> from(Long from) {
				return Key.create(type, from);
			}
		};
	}

	static <E> ETransformer<Key<E>, Long> toLong(final Class<E> type) {
		return new ETransformer<Key<E>, Long>() {
			@Override
			public Long from(Key<E> from) {
				return from.getId();
			}
		};
	}
}
