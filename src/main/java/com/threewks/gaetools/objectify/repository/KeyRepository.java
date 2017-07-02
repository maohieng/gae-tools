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

public class KeyRepository<E> extends AbstractRepository<E, Key<E>> {
	public KeyRepository(Class<E> entityType, SearchConfig searchConfig) {
		super(entityType, KeyRepository.<E> noopTransformer(), KeyRepository.<E> noopTransformer(), searchConfig);
	}

	static <E> ETransformer<Key<E>, Key<E>> noopTransformer() {
		return new ETransformer<Key<E>, Key<E>>() {
			@Override
			public Key<E> from(Key<E> from) {
				return from;
			}
		};
	}
}