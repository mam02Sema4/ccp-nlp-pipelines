/**
 * 
 */
package edu.ucdenver.ccp.nlp.uima.pipelines.dictionarylookup;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.metadata.TypeSystemDescription;

import edu.ucdenver.ccp.common.collections.CollectionsUtil;
import edu.ucdenver.ccp.common.file.FileUtil;
import edu.ucdenver.ccp.common.file.FileUtil.CleanDirectory;
import edu.ucdenver.ccp.nlp.core.mention.ClassMentionTypes;
import edu.ucdenver.ccp.nlp.ext.uima.annotators.filter.ClassMentionRemovalFilter_AE;
import edu.ucdenver.ccp.nlp.uima.pipelines.dictionarylookup.ConceptMapperDictionaryFileFactory.DictionaryNamespace;
import edu.ucdenver.ccp.nlp.uima.pipelines.dictionarylookup.ConceptMapperPipelineCmdOpts.DictionaryParameterOperation;
import edu.ucdenver.ccp.nlp.wrapper.conceptmapper.ConceptMapperAggregateFactory;
import edu.ucdenver.ccp.nlp.wrapper.conceptmapper.ConceptMapperFactory.SearchStrategyParamValue;
import edu.ucdenver.ccp.nlp.wrapper.conceptmapper.ConceptMapperFactory.TokenNormalizerConfigParam.CaseMatchParamValue;
import edu.ucdenver.ccp.nlp.wrapper.conceptmapper.typesystem.ConceptMapper2CCPTypeSystemConverter_AE;

/**
 * @author Center for Computational Pharmacology, UC Denver; ccpsupport@ucdenver.edu
 * 
 */
public class ConceptMapperPipelineFactory {

	private static final Logger logger = Logger.getLogger(ConceptMapperPipelineFactory.class);
	
	/**
	 * a collection of relevant type systems represented as strings that are relevant to running the
	 * ConceptMapper
	 */
	public static Collection<String> CONCEPTMAPPER_TYPE_SYSTEM_STRS = CollectionsUtil.createList(
			"edu.ucdenver.ccp.nlp.wrapper.conceptmapper.TypeSystem", "analysis_engine.primitive.DictTerm",
			"org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");

	/**
	 * Returns an aggregate: sentence detector, offset tokenizer, conceptmapper
	 * 
	 * @param tsd
	 * @param cmdOptions
	 * @return
	 * @throws IOException
	 * @throws UIMAException
	 */
	public static List<AnalysisEngineDescription> getPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions) throws UIMAException, IOException {

		File cmDictionaryFile = cmdOptions.getDictionaryFile();
		FileUtil.validateFile(cmDictionaryFile);

		CaseMatchParamValue caseMatchParamValue = CaseMatchParamValue.CASE_FOLD_DIGITS;
		SearchStrategyParamValue searchStrategyParamValue = SearchStrategyParamValue.CONTIGUOUS_MATCH;
		Class<? extends Annotation> spanFeatureStructureClass = cmdOptions.getSpanClass();
		AnalysisEngineDescription conceptMapperAggregateDesc = ConceptMapperAggregateFactory
				.getOffsetTokenizerConceptMapperPipelineDescription(tsd, cmDictionaryFile, caseMatchParamValue,
						searchStrategyParamValue, spanFeatureStructureClass);

		/* Converts from the CM OntologyTerm annotation class to CCPTextAnnotation classes */
		AnalysisEngineDescription cmToCcpTypeSystemConverterDesc = ConceptMapper2CCPTypeSystemConverter_AE
				.createAnalysisEngineDescription(tsd);

		/* Removes all token annotations as we don't want them to be output as RDF */
		AnalysisEngineDescription tokenRemovalDesc = ClassMentionRemovalFilter_AE.createAnalysisEngineDescription(tsd,
				new String[] { ClassMentionTypes.TOKEN });

		/* @formatter:off */
		return CollectionsUtil.createList(
				conceptMapperAggregateDesc,
				cmToCcpTypeSystemConverterDesc,
				tokenRemovalDesc); 
		/* @formatter:on */
	}

	public static List<AnalysisEngineDescription> getEntrezGenePipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.EG, workDirectoryOp);
	}

	public static List<AnalysisEngineDescription> getCellTypePipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.CL, workDirectoryOp);
	}

	public static List<AnalysisEngineDescription> getChebiPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.CHEBI, workDirectoryOp);
	}

	public static List<AnalysisEngineDescription> getSequenceOntologyPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.SO, workDirectoryOp);
	}

	public static List<AnalysisEngineDescription> getProteinOntologyPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.PR, workDirectoryOp);
	}

	public static List<AnalysisEngineDescription> getGoCcPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.GO_CC, workDirectoryOp);
	}

	public static List<AnalysisEngineDescription> getGoBpPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.GO_BP, workDirectoryOp);
	}

	public static List<AnalysisEngineDescription> getGoMfPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.GO_MF, workDirectoryOp);
	}
	
	public static List<AnalysisEngineDescription> getNcbiTaxonPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			CleanDirectory workDirectoryOp) throws UIMAException, IOException {
		return getPipelineAeDescriptions(tsd, cmdOptions, dictParamOp, DictionaryNamespace.NCBI_TAXON, workDirectoryOp);
	}

	private static List<AnalysisEngineDescription> getPipelineAeDescriptions(TypeSystemDescription tsd,
			ConceptMapperPipelineCmdOpts cmdOptions, DictionaryParameterOperation dictParamOp,
			DictionaryNamespace oboNamespace, CleanDirectory workDirectoryOp) throws UIMAException, IOException {

		File workDirectory = null;
		if (dictParamOp.equals(DictionaryParameterOperation.IGNORE)) {
			workDirectory = FileUtil.createTemporaryDirectory("cmDictBuildDirectory");
		} else if (dictParamOp.equals(DictionaryParameterOperation.TREAT_AS_DIRECTORY)) {
			workDirectory = cmdOptions.getDictionaryFile();
			FileUtil.validateDirectory(workDirectory);
		}

		if (workDirectory != null) {
			logger.info("Creating ConceptMapper dictionary file in " + workDirectory.getAbsolutePath());
			File cmDictFile = ConceptMapperDictionaryFileFactory.createDictionaryFile(oboNamespace, workDirectory,
					workDirectoryOp);
			logger.info("Concept Mapper dictionary file: " + cmDictFile);
			cmdOptions.setDictionaryFile(cmDictFile);
		}

		return getPipelineAeDescriptions(tsd, cmdOptions);
	}

}