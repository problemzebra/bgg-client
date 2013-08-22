package co.yellowbricks.bggclient.search;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.yellowbricks.bggclient.common.NoItemsFoundException;
import co.yellowbricks.bggclient.config.SpringContext;
import co.yellowbricks.bggclient.search.domain.SearchOutput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringContext.class)
public class SearchServiceTest {

	@Inject private SearchService searchService;
	
	@Test
	public void shouldReturnCorrectAmountOfDominionGames() throws SearchException, NoItemsFoundException {
		SearchOutput items = searchService.search("dominion");
		
		Assert.assertThat(items.getTotal(), CoreMatchers.is(67));
	}
	
	@Test(expected = NoItemsFoundException.class)
	public void shouldFindNoItems() throws SearchException, NoItemsFoundException {
		searchService.search("a game that should not be exist");
	}
}
