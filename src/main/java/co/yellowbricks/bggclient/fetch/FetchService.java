package co.yellowbricks.bggclient.fetch;

import javax.inject.Inject;

import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import co.yellowbricks.bggclient.common.NoItemsFoundException;
import co.yellowbricks.bggclient.common.domain.Thing;
import co.yellowbricks.bggclient.fetch.domain.FetchItem;
import co.yellowbricks.bggclient.fetch.domain.FetchItems;
import co.yellowbricks.bggclient.request.BggService;
import co.yellowbricks.bggclient.request.BggServiceException;

@Service
public class FetchService {

	@Inject @Fetch private Jaxb2Marshaller jaxb2Marshaller;
	@Inject private BggService bgg;
	
	public FetchItem fetch(int id) throws FetchException, NoItemsFoundException {
		try {
			FetchItems items = (FetchItems) jaxb2Marshaller.unmarshal(bgg.fetch(Thing.BOARDGAME, id));

			if (!CollectionUtils.isEmpty(items.getItems())) {
				return items.getItems().get(0);
			}
			throw new NoItemsFoundException();
		} catch (XmlMappingException e) { 
			throw new FetchException("While fetching id: " + id, e);
		} catch (BggServiceException e) {
			throw new FetchException("While fetching id: " + id, e);
		}
	}
}