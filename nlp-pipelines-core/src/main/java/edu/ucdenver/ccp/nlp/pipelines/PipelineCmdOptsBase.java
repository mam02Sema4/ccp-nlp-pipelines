package edu.ucdenver.ccp.nlp.uima.pipelines;

import java.io.File;

import org.kohsuke.args4j.Option;

public class PipelineCmdOptsBase {

	@Option(name = "-i", usage = "the path to the input file or directory", required = true)
	private String inputFileOrDirectoryPath;

	@Option(name = "-o", usage = "the path to the output file or directory", required = true)
	private String outputFileOrDirectoryPath;

	@Option(name = "-s", usage = "the number of files to skip before processing starts")
	private int numToSkip;

	@Option(name = "-n", usage = "the number of files to process")
	private int numToProcess;

	@Option(name = "-b", usage = "the batch number (used in the output file name)", required = true)
	private int batchNumber;

	@Option(name = "-r", usage = "Used in Medline processing pipelines. If set to true, all Medline records missing abstracts are left un-processed.")
	private boolean requirePresenceOfAbstract = false;

	@Option(name = "-z", usage = "the obo file to use if hierarchical evaluation is being conducted", required = false)
	private File oboFile;
	
	
	public int getNumToSkip() {
		return numToSkip;
	}
	
	public void setNumToSkip(int numToSkip) {
		this.numToSkip = numToSkip;
	}

	public int getNumToProcess() {
		return numToProcess;
	}

	public void setNumToProcess(int numToProcess) {
		this.numToProcess = numToProcess;
	}
	
	/**
	 * @return the batchNumber
	 */
	public int getBatchNumber() {
		return batchNumber;
	}
	
	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	/**
	 * @return the inputFileOrDirectoryPath
	 */
	public File getInputFileOrDirectoryPath() {
		return new File(inputFileOrDirectoryPath);
	}
	
	public void setInputFileOrDirectoryPath(File inputPath) {
		this.inputFileOrDirectoryPath = inputPath.getAbsolutePath();
	}

	/**
	 * @return the outputFileOrDirectoryPath
	 */
	public File getOutputFileOrDirectoryPath() {
		return new File(outputFileOrDirectoryPath);
	}

	public void setOutputFileOrDirectoryPath(File outputPath) {
		this.outputFileOrDirectoryPath = outputPath.getAbsolutePath();
	}
	
	/**
	 * @return the requirePresenceOfAbstract
	 */
	public boolean requirePresenceOfAbstract() {
		return requirePresenceOfAbstract;
	}
	
	public void setRequirePresenceOfAbstract(boolean requireAbstractPresence) {
		this.requirePresenceOfAbstract = requireAbstractPresence;
	}

	/**
	 * @return the oboFile
	 */
	public File getOboFile() {
		return oboFile;
	}

	/**
	 * @param oboFile the oboFile to set
	 */
	public void setOboFile(File oboFile) {
		this.oboFile = oboFile;
	}

}