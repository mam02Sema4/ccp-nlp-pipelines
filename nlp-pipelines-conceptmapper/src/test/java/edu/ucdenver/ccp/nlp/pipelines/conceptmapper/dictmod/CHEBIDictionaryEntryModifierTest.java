package edu.ucdenver.ccp.nlp.pipelines.conceptmapper.dictmod;

/*
 * #%L
 * Colorado Computational Pharmacology's NLP pipelines
 * 							module
 * %%
 * Copyright (C) 2014 - 2017 Regents of the University of Colorado
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Regents of the University of Colorado nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import edu.ucdenver.ccp.common.collections.CollectionsUtil;
import edu.ucdenver.ccp.nlp.pipelines.conceptmapper.dictmod.CHEBIDictionaryEntryModifier;
import edu.ucdenver.ccp.nlp.wrapper.conceptmapper.dictionary.obo.OboToDictionary.Concept;
import java.util.ArrayList;
import java.util.List;


public class CHEBIDictionaryEntryModifierTest {

	/**
	 * The following test demonstrates how you might test a
	 * DictionaryEntryModifier
	 */
	@Test
	public void testCHEBIDictEntryModifier() {
		CHEBIDictionaryEntryModifier dictModifier = new CHEBIDictionaryEntryModifier();
		List<String> conceptList = new ArrayList<String>();
		conceptList.add("http://purl.obolibrary.org/obo/CHEBI_90880");
		conceptList.add("http://purl.obolibrary.org/obo/CHEBI_24433");
		conceptList.add("http://purl.obolibrary.org/obo/CHEBI_50906");
		conceptList.add("http://purl.obolibrary.org/obo/CHEBI_33232");
		
		
		for (int i = 0; i < conceptList.size(); i++) {
			/* create your input concept */
			Concept inputConcept = new Concept(conceptList.get(i), "label", CollectionsUtil.createSet("synonym1", "synonym2"),
					null);
			/* modify the input concept using the dictionary entry modifier */
			Concept modifiedConcept = dictModifier.modifyConcept(inputConcept);
	
			/*
			 * Create the concept you expect to be returned by the dictionary entry
			 * modifier. In this case there are no changes as the
			 * SampleDictionaryEntryModifier makes no changes.
			 */
	//		Concept expectedModifiedConcept = new Concept("GO:0001234", "label",
	//				CollectionsUtil.createSet("synonym1", "synonym2"), null);
	
			/*
			 * The assert statement tests that the expected modified concept is
			 * equal to what was actually returned by the dictionary entry modifier
			 */
	//		assertEquals(expectedModifiedConcept, modifiedConcept);
			assertNull(modifiedConcept);
		}
	}

}


////DELETE ALL TERMS BELOW
////Delete (1+) - not in CRAFT
//if (inputConcept.getIdentifier().equals("http://purl.obolibrary.org/obo/CHEBI_90880")) {
//	return null;
//}
//
//// Delete group
//if (inputConcept.getIdentifier().equals("http://purl.obolibrary.org/obo/CHEBI_24433")) {
//	return null;
//}
//
//// Delete role - not in dict
//if (inputConcept.getIdentifier().equals("http://purl.obolibrary.org/obo/CHEBI_50906")) {
//	return null;
//}		
//
//// Delete application
//if (inputConcept.getIdentifier().equals("http://purl.obolibrary.org/obo/CHEBI_33232")) {
//	return null;
//}