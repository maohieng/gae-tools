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
package com.threewks.gaetools.search;

/**
 * The set of field search operators which can be supplied to {@link Search#field(String, Is, Object)}.
 * The interpretation of these is dependent on the implementation and data type the field was indexed as.
 */
public enum Is {
    EqualTo("="),
    GreaterThan(">"),
    GreaterThanOrEqualTo(">="),
    Is("is"),
    LessThanOrEqualTo("<="),
    LessThan("<"),
    Near("near"),
    Like("like");

    private String toString;

    private Is(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
