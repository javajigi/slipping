package net.slipp.study.di;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DateMessageProviderTest {
	  @Test
	  public void 오전() throws Exception {
	    DateMessageProvider provider = new DateMessageProvider();
	    assertThat(provider.getDateMessage(), is("오전"));
	  }

	  @Test
	  public void 오후() throws Exception {
	    DateMessageProvider provider = new DateMessageProvider();
	    assertThat(provider.getDateMessage(), is("오후"));
	  }
}
