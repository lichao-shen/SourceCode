package uk.co.pshealth.pslink.processor;


import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class LdapSearchProcessor implements Processor {

	
	public void process(Exchange exchange) throws Exception {
        List<?> data = exchange.getIn().getBody(List.class); 
        StringBuilder result = new StringBuilder();
        
        for (Object obj : data) {
            if (obj instanceof SearchResult) {
                SearchResult searchResult = (SearchResult)obj;
                // Write out the search result name
                result.append(searchResult.getName() + "\n");
                
                // Write out the attributes
                Attributes attributes = searchResult.getAttributes();
                NamingEnumeration<? extends Attribute> attrs = attributes.getAll();
                while (attrs.hasMore()) {
                    Attribute attribute = attrs.next();
                    System.out.println(" the attribute Id is " + attribute.getID());
                }
            }
        }
        exchange.getIn().setBody(result.toString());
    }
	
	
}
