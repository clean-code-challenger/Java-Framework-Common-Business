package common.extern.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil 
{
	private static final int BUFFER_ONCE_BYTE_SIZE = 1024 * 8;
	/**
	 * @param fileSrc 
	 * @param fileCopy 
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 AM 10:33:11
	 * @since ModelWeb 1.0
	 */
	public static int fileCopy(String fileSrc, String fileCopy)
			throws Throwable {
		int result = -1;
		try {
			if (fileSrc != null && fileCopy != null) {
				try {
					DataInputStream fileIn = null;
					DataOutputStream fileOut = null;

					fileIn = new DataInputStream(new BufferedInputStream(
							new FileInputStream(fileSrc)));
					try {
						fileOut = new DataOutputStream(
								new BufferedOutputStream(new FileOutputStream(
										fileCopy)));
					} catch (FileNotFoundException ex) {
						int nSlashPos = fileCopy.lastIndexOf("\\");
						if (nSlashPos != -1) {
							String foldername = fileCopy
									.substring(0, nSlashPos);
							int resultFolderMake = makeFolder(foldername);
							if (resultFolderMake != -1) {
								fileOut = new DataOutputStream(
										new BufferedOutputStream(
												new FileOutputStream(fileCopy)));
							}
						}
					}

					if (fileIn != null && fileOut != null) {
						byte[] reBuf = new byte[BUFFER_ONCE_BYTE_SIZE + 1];
						int nResult;
						do {
							nResult = fileIn.read(reBuf, 0,
									BUFFER_ONCE_BYTE_SIZE);
							if (nResult != -1)
								fileOut.write(reBuf, 0, nResult);
						} while (nResult != -1);
						fileOut.close();
						fileIn.close();
					}
					result = 1;
				} catch (Throwable ex) {
					throw ex;
				}
			}
		} catch (Throwable ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * Copy file
	 * @param fileSrc
	 * @param fileCopy
	 * @return
	 * @throws Throwable
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2012. 12. 17 AM 7:12:13
	 * @since ModelWeb
	 */
	public static int fileCopy(File fileSrc, String fileCopy) throws Throwable {
		int result = -1;
		try {
			if (fileSrc != null && fileCopy != null) {
				try {
					DataInputStream fileIn = null;
					DataOutputStream fileOut = null;

					fileIn = new DataInputStream(new BufferedInputStream(
							new FileInputStream(fileSrc)));
					try {
						fileOut = new DataOutputStream(
								new BufferedOutputStream(new FileOutputStream(
										fileCopy)));
					} catch (FileNotFoundException ex) {
						int nSlashPos = fileCopy.lastIndexOf("\\");
						if (nSlashPos != -1) {
							String foldername = fileCopy
									.substring(0, nSlashPos);
							int resultFolderMake = makeFolder(foldername);
							if (resultFolderMake != -1) {
								fileOut = new DataOutputStream(
										new BufferedOutputStream(
												new FileOutputStream(fileCopy)));
							}
						}
					}

					if (fileIn != null && fileOut != null) {
						byte[] reBuf = new byte[BUFFER_ONCE_BYTE_SIZE + 1];
						int nResult;
						do {
							nResult = fileIn.read(reBuf, 0,
									BUFFER_ONCE_BYTE_SIZE);
							if (nResult != -1)
								fileOut.write(reBuf, 0, nResult);
						} while (nResult != -1);
						fileOut.close();
						fileIn.close();
					}
					result = 1;
				} catch (Throwable ex) {
					throw ex;
				}
			}
		} catch (Throwable ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param folderSrc
	 * @param folderCopy
	 * @return
	 * @throws Throwable
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2012. 12. 17 AM 7:25:02
	 * @since ModelWeb
	 */
	public static int directoryCopy(String folderSrc, String folderCopy)
			throws Throwable {
		int result = -1;
		try {
			if (folderSrc != null && folderCopy != null) {
				if (FileUtil.isDirectory(folderSrc)) {
					FileUtil.makeFolder(folderCopy);
					File folder = new File(folderSrc);
					File[] fileList = folder.listFiles();
					for (int nIndex = 0; nIndex < fileList.length; nIndex++) {
						directoryCopy(folderSrc + "/"
								+ fileList[nIndex].getName(), folderCopy + "/"
								+ fileList[nIndex].getName());
					}
				} else if (FileUtil.IsThereFileExist(folderSrc)) {
					fileCopy(folderSrc, folderCopy);
				} else if (!FileUtil.IsThereFileExist(folderSrc)) {

				}
			}
		} catch (Throwable ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strPhysicalFilePath
	 * @param strContent
	 * @param strCharSet
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 31 AM 8:23:28
	 * @since ModelWeb 1.0
	 */
	public static boolean writeString(String strPhysicalFilePath,
			String strContent, String strCharSet) {
		try {
			FileOutputStream fos = new FileOutputStream(strPhysicalFilePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos, Charset
					.forName(strCharSet));
			osw.write(strContent);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * @param folderePath
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 AM 10:33:27
	 * @since ModelWeb 1.0
	 */
	public static int makeFolder(String folderePath) throws Throwable {
		int result = -1;
		try {
			File dir = new File(folderePath);
			if (!dir.isDirectory()) {
				if (dir.mkdirs()) {
					return 1;
				}
			} else {
				result = 1;
			}
		} catch (Throwable ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 AM 10:33:55
	 * @since ModelWeb 1.0
	 */
	public static int deleteFile(String filePath) throws Throwable {
		int result = -1;
		try {
			File pFile = new File(filePath);
			if (pFile.delete()) {
				result = 1;
			} else if (!pFile.exists()) {
				result = -2;
			}
		} catch (Throwable ex) {
		}
		return result;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 AM 10:33:55
	 * @since ModelWeb 1.0
	 */
	public static int deleteDirectory(String dirPath) throws Throwable {
		int result = -1;
		try {
			File folder = new File(dirPath);
			if (folder.isDirectory()) {
				if (!folder.delete()) {
					int i;
					String strChildArr[] = folder.list();
					for (i = 0; i < strChildArr.length; i++) {
						deleteDirectory(dirPath + "\\" + strChildArr[i]);
					}
					if (folder.delete()) {
						result = 1;
					} else if (!folder.exists()) {
						result = -2;
					}
				} else {
					if (folder.delete()) {
						result = 1;
					} else if (!folder.exists()) {
						result = -2;
					}
				}
			} else {
				if (folder.delete()) {
					result = 1;
				} else if (!folder.exists()) {
					result = -2;
				}
			}
		} catch (Throwable ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 15 PM 5:14:24
	 * @since ModelWeb 1.0
	 */
	public static Calendar getLasModifyDate(String strPath) {
		Calendar dtResult = null;
		File file = new File(strPath);
		if (!file.isFile())
			return null;
		dtResult = Calendar.getInstance();
		dtResult.setTimeInMillis(file.lastModified());
		return dtResult;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 AM 10:33:55
	 * @since ModelWeb 1.0
	 */
	public static int fileRename(String strPath, String strRename)
			throws Throwable {
		int result = -1;
		try {
			File file = new File(strPath);
			File fileRename = new File(strRename);
			if (file.exists()) {
				if (file.renameTo(fileRename)) {
					result = 1;
				}
			} else {
				result = -3;
			}
		} catch (Throwable ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 31 AM 8:23:16
	 * @since ModelWeb 1.0
	 */
	public static boolean exist(String filepath) {
		if (filepath != null) {
			File file = new File(filepath);
			return file.exists();
		} else {
			return false;
		}
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 17 PM 3:52:04
	 * @since ModelWeb 1.0
	 */
	public static boolean isDirectory(String dirPath) throws Throwable {
		if (dirPath != null) {
			File file = new File(dirPath);
			if (!file.exists()) {
				return false;
			}
			if (file.isDirectory()) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 31 AM 8:22:57
	 * @since ModelWeb 1.0
	 */
	public byte[] getFileData(String strPath) {
		byte[] buffer = null;
		if (strPath.length() <= 0)
			return null;

		File file = new File(strPath);
		if (!file.isFile())
			return null;
		buffer = new byte[(int) (file.length())];
		try {
			FileInputStream fis = new FileInputStream(file);
			fis.read(buffer);
			fis.close();
		} catch (Exception e) {
			return null;
		}
		return buffer;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 31 AM 8:22:46
	 * @since ModelWeb 1.0
	 */
	public static boolean IsThereFileExist(String strPhysicalFilePath) {
		if (strPhysicalFilePath == "")
			return false;
		File file = new File(strPhysicalFilePath);
		return file.isFile();
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 31 AM 8:22:22
	 * @since ModelWeb 1.0
	 */
	public static long getFileSize(String filePath) {
		long nSize = 0;
		File f = new File(filePath);
		if (f.exists()) {
			nSize = f.length();
		}
		return nSize;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 31 AM 8:22:24
	 * @since ModelWeb 1.0
	 */
	public static long getDirectorySize(String dirPath) {
		long nSize = 0;
		File f = new File(dirPath);
		if (!f.isDirectory())
			return 0;
		File[] files = f.listFiles();
		for (int nIndex = 0; nIndex < files.length; nIndex++) {
			if (files[nIndex].isDirectory())
				nSize += getDirectorySize(files[nIndex].getPath());
			else
				nSize += files[nIndex].length();
		}
		return nSize;
	}
	/****************************Zip****************************/
	/****************************Zip****************************/
	/****************************Zip****************************/
	/**
	 * @param fileSrc
	 * @param fileCopy
	 * @return
	 * @throws Throwable
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2012. 12. 14 PM 5:42:05
	 * @since ModelWeb
	 */
	public static int ZipFile(String fileSrc, String fileTarget, String rootName) throws Throwable {
		try {
			File fileSource = new File(fileSrc);
			if (fileSource.exists()) {
				ZipOutputStream zipOutput = new ZipOutputStream(
						new DataOutputStream(new FileOutputStream(fileTarget)));
				ZipFile(fileSrc, fileTarget, rootName, zipOutput);
				zipOutput.close();
			}
		} catch (Exception ex) {
		}
		return -1;
	}
	/**
	 * @param dirPath
	 * @param strPath
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2012. 12. 14 PM 5:48:56
	 * @since ModelWeb
	 */
	private static int ZipFile(String fileSrc, String fileTarget,
			String rootName, ZipOutputStream zipOutput) throws Throwable {
		try {
			File fileSource = new File(fileSrc);
			if (fileSource.exists()) {
				if (fileSource.isDirectory()) {
					int i;
					String strChildArr[] = fileSource.list();
					for (i = 0; i < strChildArr.length; i++) {
						ZipFile(fileSrc + "/" + strChildArr[i], fileTarget,
								rootName + "/" + strChildArr[i], zipOutput);
					}
				} else {
					int size = 0;
					byte[] tmp = new byte[1024 * 8];
					FileInputStream fi = new FileInputStream(fileSrc);
					ZipEntry zipEntryItem = new ZipEntry(rootName);
					zipOutput.putNextEntry(zipEntryItem);
					while ((size = fi.read(tmp)) > 0) {
						zipOutput.write(tmp, 0, size);
						zipOutput.flush();
					}
					fi.close();
				}
			}
		} catch (Exception ex) {
		}
		return -1;
	}
	/** **************************Zip*************************** */
	/****************************Zip****************************/
	/****************************Zip****************************/
}
