/**
 * Copyright Linebee. www.linebee.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linebee.solrmeter.mock;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

public class SolrServerMock extends CommonsHttpSolrServer {
	
	private static final long serialVersionUID = 7266180569831920295L;

	private int numberOfCommits;
	
	private int numberOfOptimize;
	
	private List<SolrInputDocument> addedDocuments;
	
	public SolrServerMock() throws MalformedURLException {
		super("http://0.0.0.0:8080/solr");
		addedDocuments = new LinkedList<SolrInputDocument>();
	}
	
	@Override
	public UpdateResponse commit() throws SolrServerException, IOException {
		numberOfCommits++;
		UpdateResponse response = new UpdateResponse();
		return response;
	}
	
	@Override
	public UpdateResponse add(SolrInputDocument doc)
			throws SolrServerException, IOException {
		Logger.getLogger(this.getClass()).debug("adding document" + doc);
		addedDocuments.add(doc);
		UpdateResponse response = new UpdateResponse();
		return response;
	}

	@Override
	public NamedList<Object> request(SolrRequest arg0)
			throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UpdateResponse optimize() throws SolrServerException, IOException {
		numberOfOptimize++;
		UpdateResponse response = new UpdateResponse();
		return response;
	}

	public List<SolrInputDocument> getAddedDocuments() {
		return addedDocuments;
	}

	public void setAddedDocuments(List<SolrInputDocument> addedDocuments) {
		this.addedDocuments = addedDocuments;
	}

	public int getNumberOfOptimize() {
		return numberOfOptimize;
	}

	public int getNumberOfCommits() {
		return numberOfCommits;
	}

	public void setNumberOfCommits(int numberOfCommits) {
		this.numberOfCommits = numberOfCommits;
	}

}
