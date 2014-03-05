package analytic.ofofo.net.apis.gmail.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class FileUtils {
	public static String tail(File file) {
		RandomAccessFile fileHandler = null;
		try {
			fileHandler = new RandomAccessFile(file, "r");
			long fileLength = fileHandler.length() - 1;
			StringBuilder sb = new StringBuilder();

			for (long filePointer = fileLength; filePointer != -1; filePointer--) {
				fileHandler.seek(filePointer);
				int readByte = fileHandler.readByte();

				if (readByte == 0xA) {
					if (filePointer == fileLength) {
						continue;
					} else {
						break;
					}
				} else if (readByte == 0xD) {
					if (filePointer == fileLength - 1) {
						continue;
					} else {
						break;
					}
				}

				sb.append((char) readByte);
			}

			String lastLine = sb.reverse().toString();
			return lastLine;
		} catch (java.io.FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (java.io.IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fileHandler != null)
				try {
					fileHandler.close();
				} catch (IOException e) {
					/* ignore */
				}
		}
	}

	public static String tail(File file, int lines) {
		java.io.RandomAccessFile fileHandler = null;
		try {
			fileHandler = new java.io.RandomAccessFile(file, "r");
			long fileLength = fileHandler.length() - 1;
			StringBuilder sb = new StringBuilder();
			int line = 0;

			for (long filePointer = fileLength; filePointer != -1; filePointer--) {
				fileHandler.seek(filePointer);
				int readByte = fileHandler.readByte();

				if (readByte == 0xA) {
					if (line == lines) {
						if (filePointer == fileLength) {
							continue;
						} else {
							break;
						}
					}
				} else if (readByte == 0xD) {
					line = line + 1;
					if (line == lines) {
						if (filePointer == fileLength - 1) {
							continue;
						} else {
							break;
						}
					}
				}
				sb.append((char) readByte);
			}

			sb.deleteCharAt(sb.length() - 1);
			String lastLine = sb.reverse().toString();
			return lastLine;
		} catch (java.io.FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (java.io.IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fileHandler != null)
				try {
					fileHandler.close();
				} catch (IOException e) {
					/* ignore */
				}
		}
	}
	
	public static void tail(File src, OutputStream out, int maxLines) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(src));
		String[] lines = new String[maxLines];
		int lastNdx = 0;
		for (String line = reader.readLine(); line != null; line = reader
				.readLine()) {
			if (lastNdx == lines.length) {
				lastNdx = 0;
			}
			lines[lastNdx++] = line;
		}

		OutputStreamWriter writer = new OutputStreamWriter(out);
		for (int ndx = lastNdx; ndx != lastNdx - 1; ndx++) {
			if (ndx == lines.length) {
				ndx = 0;
			}
			writer.write(lines[ndx]);
			writer.write("\n");
		}

		writer.flush();
	}
	
	public static String [] tailer(File src, int maxLines) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(src));
		String[] lines = new String[maxLines];
		int lastNdx = 0;
		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			if (lastNdx == lines.length) {
				lastNdx = 0;
			}
			lines[lastNdx++] = line;
		}
		return lines;
	}
	public static void main(String[] args) {
		File file = new File("src/main/resources/mail.json");
		FileUtils fu = new FileUtils();
		//System.out.println(fu.tail(file));
		try {
			System.out.println(fu.tailer(file, 100)[55]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
