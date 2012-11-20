/*
 * Copyright 2012 Tamas Blummer tamas@bitsofproof.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bitsofproof.supernode.main;

import java.io.IOException;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bitsofproof.supernode.model.TransactionValidationException;

public class Audit
{
	private static final Logger log = LoggerFactory.getLogger (Audit.class);

	public void start (String[] args) throws IOException, TransactionValidationException
	{
		final CommandLineParser parser = new GnuParser ();
		final Options gnuOptions = new Options ();
		gnuOptions.addOption ("h", "help", false, "I can't help you yet");
		gnuOptions.addOption ("f", "full", false, "Full validation");

		try
		{
			parser.parse (gnuOptions, args);
		}
		catch ( ParseException e1 )
		{
			log.error ("Invalid options ", e1);
			return;
		}

	}

	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext ()
	{
		return context;
	}

	public static void main (String[] args)
	{
		try
		{
			log.info ("bitsofproof audit (c) 2012 Tamas Blummer tamas@bitsofproof.com");
			log.trace ("Spring context setup");
			context = new ClassPathXmlApplicationContext ("audit.xml");
			Audit application = context.getBean (Audit.class);
			application.start (args);
		}
		catch ( Exception e )
		{
			log.error ("Application", e);
		}
	}

}
