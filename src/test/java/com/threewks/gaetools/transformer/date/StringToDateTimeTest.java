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
package com.threewks.gaetools.transformer.date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StringToDateTimeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private StringToDateTime transformer = new StringToDateTime();

    @Test
    public void shouldNotTransformNull() {
        assertThat(transformer.from(null), is(nullValue()));
    }

    @Test
    public void shouldTransformUsingSystemTimezone() {
        assertThat(transformer.from("2014-06-01T12:34:56.123"), is(new DateTime(2014, 6, 1, 12, 34, 56, 123)));
    }

    @Test
    public void shouldTransformUsingSpecifiedTimezone() {
        assertThat(transformer.from("2014-06-01T12:34:56.123Z").compareTo(new DateTime(2014, 6, 1, 12, 34, 56, 123).withZoneRetainFields(DateTimeZone.UTC)), is(0));
        assertThat(transformer.from("2014-06-01T12:34:56.123+10:00").compareTo(new DateTime(2014, 6, 1, 12, 34, 56, 123).withZoneRetainFields(DateTimeZone.forOffsetHours(10))), is(0));
    }

    @Test
    public void shouldTransformLeniently() {
        assertThat(transformer.from("2014-06-01T12:34:56.12").compareTo(new DateTime(2014, 6, 1, 12, 34, 56, 120)), is(0));
        assertThat(transformer.from("2014-06-01T12:34:56.1").compareTo(new DateTime(2014, 6, 1, 12, 34, 56, 100)), is(0));
        assertThat(transformer.from("2014-06-01T12:34:56").compareTo(new DateTime(2014, 6, 1, 12, 34, 56)), is(0));
        assertThat(transformer.from("2014-06-01T12:34").compareTo(new DateTime(2014, 6, 1, 12, 34, 0)), is(0));
        assertThat(transformer.from("2014-06-01T12").compareTo(new DateTime(2014, 6, 1, 12, 0, 0)), is(0));
        assertThat(transformer.from("2014-06-01").compareTo(new DateTime(2014, 6, 1, 0, 0, 0)), is(0));
        assertThat(transformer.from("2014-06").compareTo(new DateTime(2014, 6, 1, 0, 0, 0)), is(0));
        assertThat(transformer.from("2014").compareTo(new DateTime(2014, 1, 1, 0, 0, 0)), is(0));
        assertThat(transformer.from("2014-06-01T12:34:56+08:00").compareTo(new DateTime(2014, 6, 1, 12, 34, 56).withZoneRetainFields(DateTimeZone.forOffsetHours(8))), is(0));
        assertThat(transformer.from("2014-06-01T12:34+08:00").compareTo(new DateTime(2014, 6, 1, 12, 34, 0).withZoneRetainFields(DateTimeZone.forOffsetHours(8))), is(0));
        assertThat(transformer.from("2014-06-01T12+08:00").compareTo(new DateTime(2014, 6, 1, 12, 0, 0).withZoneRetainFields(DateTimeZone.forOffsetHours(8))), is(0));
    }

    @Test
    public void shouldRetainSpecifiedTimezone() {
        assertThat(transformer.from("2014-06-01T12:34:56.123"), is(new DateTime(2014, 6, 1, 12, 34, 56, 123)));
        assertThat(transformer.from("2014-06-01T12:34:56.123+08:00"), is(new DateTime(2014, 6, 1, 12, 34, 56, 123).withZoneRetainFields(DateTimeZone.forOffsetHours(8))));
        assertThat(transformer.from("2014-06-01T12:34:56.123-08:00"), is(new DateTime(2014, 6, 1, 12, 34, 56, 123).withZoneRetainFields(DateTimeZone.forOffsetHours(-8))));
        assertThat(transformer.from("2014-06-01T12:34:56.123Z"), is(new DateTime(2014, 6, 1, 12, 34, 56, 123).withZoneRetainFields(DateTimeZone.UTC)));
    }

    @Test
    public void shouldFailToTransformEmptyString() {
        thrown.expect(IllegalArgumentException.class);
        transformer.from("");
    }
}
