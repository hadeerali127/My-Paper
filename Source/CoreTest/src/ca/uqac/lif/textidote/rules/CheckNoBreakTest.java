/*
    TeXtidote, a linter for LaTeX documents
    Copyright (C) 2018  Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.textidote.rules;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import ca.uqac.lif.textidote.Advice;
import ca.uqac.lif.textidote.Rule;
import ca.uqac.lif.textidote.as.AnnotatedString;
import ca.uqac.lif.textidote.rules.CheckNoBreak;

public class CheckNoBreakTest 
{
	@Test
	public void test1()
	{
		AnnotatedString in_string = AnnotatedString.read(new Scanner("Lorem ipsum dolor sit amet.\n\n" +
				"Lorem ipsum dolor sit amet."));
		Rule r = new CheckNoBreak();
		List<Advice> ad_list = r.evaluate(in_string, in_string);
		assertTrue(ad_list.isEmpty());
	}
	
	@Test
	public void test2()
	{
		AnnotatedString in_string = AnnotatedString.read(new Scanner("Lorem ipsum dolor sit amet.\\\\\n" +
				"Lorem ipsum dolor sit amet."));
		Rule r = new CheckNoBreak();
		List<Advice> ad_list = r.evaluate(in_string, in_string);
		assertEquals(1, ad_list.size());
	}

	@Test
	public void test3()
	{
		AnnotatedString in_string = AnnotatedString.read(new Scanner("\\begin{align*}\n" + 
				"Lorem ipsum dolor sit amet.\\\\\n" +
				"Lorem ipsum dolor sit amet.\n"+
				"\\end{align*}"));
		Rule r = new CheckNoBreak();
		List<Advice> ad_list = r.evaluate(in_string, in_string);
		assertTrue(ad_list.isEmpty());
	}
}
