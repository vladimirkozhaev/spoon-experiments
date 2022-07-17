package diagrams;


import org.junit.Test;

import diagrams.Utils;


public class HelloWorldProcessorTest {
	@Test
	public void testStaticCallGraph() throws Exception {
		Utils.makeStaticCallGraphTrace("src/test/resources/src");
		
	}

	
}