package com.wp.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * 
 * 工程名:upload
 * 包名:com.wp.upload.util
 * 类名:WpFileUtils
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月2日-上午9:58:38 
 */
public class WpFileUtils {
	public static final long ONE_KB = 1024;
	public static final long ONE_MB = ONE_KB * ONE_KB;
	public static final long ONE_GB = ONE_KB * ONE_MB;
	// 验证字符串是否为正确路径名的正则表达式
	//private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	// 通过spath.matches(matches)方法的返回值判断是否正确
	static boolean flag = false;
	static File file;
	
	public static void sort(File[] files) {
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File file1, File file2) {
				long diff = file1.lastModified() - file2.lastModified();
				if (diff > 0)
					return -1;
				else if (diff == 0)
					return 0;
				else
					return 1;
			}
		});
	}
	/**
	 * 
	 * 获取文件类型
	 */
	public static String getFileType(String file){
		if(new File(file).isDirectory()){
			return "floder";
		}else{
			return "file";
		}
	}
	/**
	 * 
	 * 获取文件最后修改时间
	 */
	public static String getLastFileTime(String file){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new File(file).lastModified()));
	}
	/**
	 * 获取当前目录下的目录和文件（绝对路径）
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> getDirAbsolutePathAndFile(String path) {
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		if (file.isFile()) {
			list.add(file.getAbsolutePath());
			return list;
		}
		File[] files = file.listFiles();
		sort(files);
		for (File file2 : files) {
			list.add(file2.getAbsolutePath());
		}
		return list;
	}

	/**
	 * 获取当前目录下属于exts后缀的文件名。不包含目录名
	 * 
	 * @param path
	 * @param exts
	 * @return
	 */
	public static List<String> getDirectoryFileNameByExt(String path,
			String[] exts) {
		List<String> list = new ArrayList<String>();
		for (String file : getDirAbsolutePathAndFile(path)) {
			if (new File(file).isFile()) {
				String suffix = getExt(file,false);
				String filename = getFileName(file);
				for (String ext : exts) {
					if (suffix.equalsIgnoreCase(ext)) {
						list.add(filename);
					}
				}
			}
		}
		return list;
	}
	/**
	 * 获取一个文件的文件名(带有后缀)
	 * resource/system/upload/xuchengfeifei/2011/01/01/20110101144251319.jpg ==
	 * >20110101144251319.jpg
	 * 
	 * @param fileName
	 *            文件
	 * @return 返回文件的文件名
	 */
	public static String getFileName(String fileName) {
		fileName = transfer(fileName);
		int pos = fileName.lastIndexOf("/") + 1;
		if (pos == -1) {
			return fileName;
		} else {
			return fileName.substring(pos, fileName.length());
		}
	}
	/**
	 * 反斜线转换
	 */
	private static String transfer(String fileName) {
		if (fileName.indexOf("\\") != -1) {
			fileName = fileName.replaceAll("\\\\", "/");
		}
		return fileName;
	}
	/**
	 * 获取需要过滤的后缀
	 * 
	 * @param fileTypes
	 *            从数据库获取的后缀
	 * @return
	 */
	public static String[] getFileExts(String fileTypes) {
		if (fileTypes.indexOf(";") == -1) {
			return new String[] { fileTypes };
		}
		String[] exts = fileTypes.split(";");
		String[] fileExts = new String[exts.length];
		for (int i = 0; i < exts.length; i++) {
			fileExts[i] = getExt(exts[i],false);
		}
		return fileExts;
	}
	/**
	 * 
	 * 获取文件后缀名
	 * com.wp.util 
	 * 方法名：getExt
	 * 创建人：wenpeng
	 * 时间：2016年1月10日-下午3:54:53 
	 * @param name  文件名称
	 * @param flag true加点 false 没有点
	 * @return String
	 * @exception  getExt("165as.4d.txt", true) === .txt
	 * @since  1.0.0
	 */
	public static String getExt(String fileName,boolean flag) {
		String result = "";
		int pos = fileName.lastIndexOf(".");
		if (pos != -1){
			if(flag) {
				result = fileName.substring(fileName.lastIndexOf("."),fileName.length());
			}else {
				result = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			}
		}
		return result;
	}
	/**
     * 创建目录
     * 
     * @param dir 目录
     */
    public static void mkdir(String dir) {
        try {
            String dirTemp = dir;
            File dirPath = new File(dirTemp);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
        } catch (Exception e) {
            System.out.println("创建目录操作出错: "+e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 新建文件
     * 
     * @param fileName
     *            String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @param content
     *            String 文件内容
     *            
     */
    public static void createNewFile(String fileName, String content) {
        try {
            String fileNameTemp = fileName;
            File filePath = new File(fileNameTemp);
            if (!filePath.exists()) {
                filePath.createNewFile();
            }
            FileWriter fw = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fw);
            String strContent = content;
            pw.println(strContent);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("新建文件操作出错: "+e.getMessage());
            e.printStackTrace();
        }

    }
    /**
	 * 操作文件 以字节的方式进行文件写入
	 * 
	 * @param content
	 *            写入的内容
	 * @param filename
	 *            写入的文件
	 * @param encoding
	 *            写入的文件时的编码，可以为空
	 */
	public static void writeFileByChar(String content, String filename,
			String encoding) {
		File file = new File(filename);
		Writer writer = null;
		try {
			if (null == encoding || "".equals(encoding)) {
				writer = new OutputStreamWriter(new FileOutputStream(file));
			} else {
				writer = new OutputStreamWriter(new FileOutputStream(file),
						encoding);
			}
			writer.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 操作文件 以行为单位进行写入文件
	 * 
	 * @param content
	 *            写入文件的内容
	 * @param filename
	 *            文件
	 */
	public static void writeFileByLine(String content, String filename,
			boolean append) {
		File file = new File(filename);
		String pathname = new File(filename).getParent();
		if (!new File(pathname).exists()) {
			new File(pathname).mkdirs();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(file, append));
			writer.print(content);
			writer.println();
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void writeContentByLine(String content, String filename,
			String encoding, boolean append) {
		File file = new File(filename);
		File pFile = new File(file.getParent());
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(file, append), encoding));
			writer.print(content);
			writer.println();
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	/**
	 * 将内容一行行写入文件中
	 * 
	 * @param content
	 * @param filename
	 */
	public static void writeFileByLine(String content, String filename) {
		File file = new File(filename);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(file));
			writer.print(content);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将内容写入文件中
	 * 
	 * @param content
	 * @param filename
	 */
	public static void writeFileByFileWriter(String content, String filename) {
		File file = new File(filename);
		Writer writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(content);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将内容写入文件中
	 * 
	 * @param content
	 * @param filename
	 */
	public static void writeFileByBufferFileWriter(String content,
			String filename) {
		File file = new File(filename);
		Writer writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将内容写入到文件中。
	 * 
	 * @param soruce
	 * @param destFile
	 * @param encoding
	 */
	public static void writerContent(String soruce, String destFile,
			String encoding) {
		try {
			FileOutputStream outputStream = new FileOutputStream(new File(
					destFile));
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					outputStream, encoding);
			BufferedWriter writer = new BufferedWriter(outputStreamWriter);
			writer.write(soruce);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将内容追加到一个文件中.
	 * 
	 * @param soruce
	 * @param destFile
	 * @param encoding
	 * @param isAppend
	 */
	public static void writerContentToFile(String soruce, File destFile,
			String encoding, boolean isAppend) {
		try {
			FileOutputStream outputStream = new FileOutputStream(destFile,
					isAppend);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					outputStream, encoding);
			BufferedWriter writer = new BufferedWriter(outputStreamWriter);
			writer.write(soruce);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writerContentToFile(String soruce, String destFile,
			String encoding, boolean isAppend) {
		writerContentToFile(soruce, new File(destFile), encoding, isAppend);
	}

	/**
	 * 操作文件之追加文件内容
	 * 
	 * @param content
	 *            追加的内容
	 * @param filename
	 *            追加的文件
	 * @param charsetName
	 *            编码 尽量使用utf-8
	 */
	public static void writerContentToFile(String content, String filename,
			String charsetName) {
		try {
			// "r", "rw", "rws", or "rwd"
			RandomAccessFile randomAccessFile = new RandomAccessFile(filename,
					"rw");
			long fileLength = randomAccessFile.length();// 获取文件字节数。
			randomAccessFile.seek(fileLength);// 将文件指针移动文件的尾部
			randomAccessFile.write(content.getBytes(charsetName));
			randomAccessFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 操作文件之追加文件内容
	 * 
	 * @param content
	 *            追加的内容
	 * @param filename
	 *            追加的文件
	 * @param flag
	 *            是否追加，false标识：覆盖之前的内容,true是追加
	 */
	public static void writerContentToFile(String content, String filename,
			boolean flag) {
		try {
			FileWriter writer = new FileWriter(filename, flag);
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一个文件的内容复制给另一个文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @param encoding
	 * @param isAppend
	 */
	public static void writerFileLinesToFile(File srcFile, File destFile,
			String encoding, boolean isAppend) {
		try {
			FileInputStream inputStream = new FileInputStream(srcFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, encoding));
			FileOutputStream outputStream = new FileOutputStream(destFile,
					isAppend);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					outputStream, encoding);
			BufferedWriter writer = new BufferedWriter(outputStreamWriter);
			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
			}
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writerFileLinesToFile(String srcFile, String destFile,
			String encoding, boolean isAppend) {
		writerFileLinesToFile(new File(srcFile), new File(destFile), encoding,
				isAppend);
	}
	public static String readFileByChars(String fileName, String encoding) {
		return readFileByChars(new File(fileName), encoding);
	}

	public static String readFileByChars(File fileName, String encoding) {
		StringBuffer buffer = new StringBuffer();
		Reader read = null;
		try {
			char[] tempchars = new char[30];
			int charread = 0;
			read = new InputStreamReader(new FileInputStream(fileName),
					encoding);
			while ((charread = read.read(tempchars)) != -1) {
				if ((charread == tempchars.length)
						&& (tempchars[tempchars.length - 1] != 'r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == 'r') {
							continue;
						} else {
							buffer.append(tempchars[i]);
						}
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e1) {
				}
			}
		}
		return buffer.toString();
	}

	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				buffer.append(tempString + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return buffer.toString();
	}

	public static List<String> readFileByLine(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				list.add(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		return list;
	}

	public static List<String> readFileByLine(String fileName, String encoding) {
		File file = new File(fileName);
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try {
			FileInputStream inputStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(inputStream,
					encoding));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				list.add(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		return list;
	}
	public static String readFile(File file, String encoding) {
		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, encoding);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				buffer.append(tempString + "\n");
			}
			reader.close();
		} catch (Exception e) {
		}
		return buffer.toString();
	}

	public static String readFile(String filename, String encoding) {
		return readFile(new File(filename), encoding);
	}

	public static String readFile(File file) {
		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				buffer.append(tempString + "\n");
			}
			reader.close();
		} catch (Exception e) {
		}
		return buffer.toString();
	}

	public static String readFile(String filename) {
		return readFile(new File(filename));
	}

	public static String readFiles(File[] files, String encoding) {
		StringBuffer buffer = new StringBuffer();
		for (File file : files) {
			buffer.append(readFile(file, encoding));
		}
		return buffer.toString();
	}

	public static String readFiles(String[] files, String encoding) {
		StringBuffer buffer = new StringBuffer();
		for (String pathname : files) {
			File file = new File(pathname);
			buffer.append(readFile(file, encoding));
		}
		return buffer.toString();
	}
    /**
     * 删除文件
     * 
     * @param fileName 包含路径的文件名
     */
    public static void delFile(String fileName) {
        try {
            String filePath = fileName;
            File delFile = new File(filePath);
            delFile.delete();
        } catch (Exception e) {
        	System.out.println("删除文件操作出错: "+e.getMessage());
            e.printStackTrace();
        }
    }
    

    /**
     * 删除文件夹
     * 
     * @param folderPath  文件夹路径
     */
    public static void delFolder(String folderPath) {
        try {
            // 删除文件夹里面所有内容
            delAllFile(folderPath); 
            String filePath = folderPath;
            File myFilePath = new File(filePath);
            // 删除空文件夹
            myFilePath.delete(); 
        } catch (Exception e) {
        	System.out.println("删除文件夹操作出错"+e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 删除文件夹里面的所有文件
     * 
     * @param path 文件夹路径
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] childFiles = file.list();
        File temp = null;
        for (int i = 0; i < childFiles.length; i++) {
            //File.separator与系统有关的默认名称分隔符
            //在UNIX系统上，此字段的值为'/'；在Microsoft Windows系统上，它为 '\'。
            if (path.endsWith(File.separator)) {
                temp = new File(path + childFiles[i]);
            } else {
                temp = new File(path + File.separator + childFiles[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + childFiles[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + childFiles[i]);// 再删除空文件夹
            }
        }
    }
    /**
     * 复制单个文件
     * 
     * @param srcFile
     *            包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest
     *            目标文件目录；若文件目录不存在则自动创建  如：E:/phsftp/dest
     * @throws IOException
     */
    public static void copyFile(String srcFile, String dirDest) {
        try {
            FileInputStream in = new FileInputStream(srcFile);
            mkdir(dirDest);
            FileOutputStream out = new FileOutputStream(dirDest+"/"+new File(srcFile).getName());
            int len;
            byte buffer[] = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
        	System.out.println("复制文件操作出错:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 复制文件夹
     * 
     * @param oldPath
     *            String 源文件夹路径 如：E:/phsftp/src
     * @param newPath
     *            String 目标文件夹路径 如：E:/phsftp/dest
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            // 如果文件夹不存在 则新建文件夹
            mkdir(newPath);
            File file = new File(oldPath);
            String[] files = file.list();
            File temp = null;
            for (int i = 0; i < files.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + files[i]);
                } else {
                    temp = new File(oldPath + File.separator + files[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] buffer = new byte[1024 * 2];
                    int len;
                    while ((len = input.read(buffer)) != -1) {
                        output.write(buffer, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制文件夹操作出错:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /**
	 * 根据后缀过滤你要获取的文件
	 * 
	 * @param path
	 * @param type
	 *            文件后缀
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public static List<String> listFiles(String path, String[] exts) {
		if (exts == null || exts.length == 0)
			return Collections.EMPTY_LIST;
		LinkedList<File> list = new LinkedList<File>();
		ArrayList<String> listPath = new ArrayList<String>();
		File dir = new File(path);
		if (!dir.exists()) {
			return Collections.EMPTY_LIST;
		}
		File file[] = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory()) {
				list.add(file[i]);
			} else {
				for (String ext : exts) {
					if (getExt(file[i].getName(),false).equalsIgnoreCase(ext)) {
						listPath.add(file[i].getAbsolutePath());
					}
				}
			}
		}

		File tmp;
		while (!list.isEmpty()) {
			tmp = list.removeFirst();
			if (tmp.isDirectory()) {
				file = tmp.listFiles();
				if (file == null) {
					continue;
				}
				for (int i = 0; i < file.length; i++) {
					if (file[i].isDirectory()) {
						list.add(file[i]);
					} else {
						for (String ext : exts) {
							if (getExt(file[i].getName(),false).equalsIgnoreCase(ext)) {
								listPath.add(file[i].getAbsolutePath());
							}
						}
					}
				}
			}
		}
		return listPath;
	}
   /**
    * 解压zip文件
    * 
    * @param srcDir
    *            解压前存放的目录
    * @param destDir
    *            解压后存放的目录
    * @throws Exception
    */
   public static void jieYaZip(String srcDir, String destDir) throws Exception {
       int leng = 0;
       byte[] b = new byte[1024*2];
       /** 获取zip格式的文件 **/
      // File[] zipFiles = new FileFilterByExtension("zip").getFiles(srcDir); 
       File[] zipFiles = new File(srcDir).listFiles();
       if(zipFiles!=null && !"".equals(zipFiles)){
    	   mkdir(destDir);
           for (int i = 0; i < zipFiles.length; i++) {
               File file = zipFiles[i];
               /** 解压的输入流 * */
               ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
               ZipEntry entry=null;
               while ((entry=zis.getNextEntry())!=null) {
                   File destFile =null;
                   if(destDir.endsWith(File.separator)){
                       destFile = new File(destDir + entry.getName());
                   }else {
                       destFile = new File(destDir + "/" + entry.getName());
                   }
                   /** 把解压包中的文件拷贝到目标目录 * */
                   FileOutputStream fos = new FileOutputStream(destFile);
                   while ((leng = zis.read(b)) != -1) {
                       fos.write(b, 0, leng);
                   }
                   fos.close();
               }
               zis.close();
           }
       }
   }
   
   /**
    * 压缩文件
    * 
    * @param srcDir
    *            压缩前存放的目录
    * @param destDir
    *            压缩后存放的目录
    * @throws Exception
    */
   public static void yaSuoZip(String srcDir, String destDir) throws Exception {
       String tempFileName=null;
       byte[] buf = new byte[1024*2];
       int len;
       //获取要压缩的文件
       File[] files = new File(srcDir).listFiles();
       if(files!=null){
           for (File file : files) {
               if(file.isFile()){
                   FileInputStream fis = new FileInputStream(file);
                   BufferedInputStream bis = new BufferedInputStream(fis);
                   if (destDir.endsWith(File.separator)) {
                       tempFileName = destDir + file.getName() + ".zip";
                   } else {
                       tempFileName = destDir + "/" + file.getName() + ".zip";
                   }
                   FileOutputStream fos = new FileOutputStream(tempFileName);
                   BufferedOutputStream bos = new BufferedOutputStream(fos);
                   ZipOutputStream zos = new ZipOutputStream(bos);// 压缩包

                   ZipEntry ze = new ZipEntry(file.getName());// 压缩包文件名
                   zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处

                   while ((len = bis.read(buf)) != -1) {
                       zos.write(buf, 0, len);
                       zos.flush();
                   }
                   bis.close();
                   zos.close();
               
               }
           }
       }
   }
   /**
	 * 统计文件大小
	 * 
	 * @param pathname
	 * @return
	 */
	public static String countFileSize(String pathname) {
		String fileSizeString = "";
		try {
			File file = new File(pathname);
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = file.length();
			if (fileS < 1024) {
				fileSizeString = "0byte";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "KB";
			} else if (fileS < 1073741824) {
				fileSizeString = df
						.format(((double) fileS / 1024 / 1024) - 0.01)
						+ "MB";
			} else {
				fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
						+ "G";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileSizeString;
	}

	/**
	 * 根据File文件的长度统计文件的大小
	 * 
	 * @param size
	 *            File的长度 file.lenght()
	 * @return 返回文件大小
	 */
	public static String countFileSize(long fileSize) {
		String fileSizeString = "";
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = fileSize;
			if (fileS == 0) {
				fileSizeString = "0KB";
			} else if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "B";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "KB";
			} else if (fileS < 1073741824) {
				fileSizeString = df
						.format(((double) fileS / 1024 / 1024) - 0.01)
						+ "MB";
			} else {
				fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
						+ "G";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileSizeString;
	}
	/**
	 * 根据File文件的长度统计文件的大小
	 * 
	 * @param size
	 *            File的长度 file.lenght()
	 * @return 返回文件大小
	 */
	public static String byteCountToDisplaySize(long size) {
		String displaySize;
		if (size / ONE_GB > 0) {
			displaySize = String.valueOf(size / ONE_GB) + " G";
		} else if (size / ONE_MB > 0) {
			displaySize = String.valueOf(size / ONE_MB) + " MB";
		} else if (size / ONE_KB > 0) {
			displaySize = String.valueOf(size / ONE_KB) + " KB";
		} else {
			displaySize = String.valueOf(size) + " bytes";
		}
		return displaySize;
	}
	/**
	 * 
	 * 图片下载
	 * @param filePath	 保存图片的路径
	 * @param imageUrl	 请求的URL路径
	 * @param fileName	保存的图片名字
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean downImg(String filePath, String imageUrl,
			String fileName) {
		try {
			File files = new File(filePath);
			if (!files.exists()) {
				files.mkdirs();
			}
			URL url = new URL(imageUrl);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			if (!filePath.endsWith("/")) {
				filePath = filePath + "/";
			}
			File file = new File(filePath + fileName);
			FileOutputStream out = new FileOutputStream(file);
			int i = 0;
			while ((i = is.read()) != -1) {
				out.write(i);
			}
			is.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 
	 * @param filePath	 保存图片的路径
	 * @param imageUrl	 请求的URL路径
	 * @param fileName	保存的图片名字
	 * @return Map<String,Object>  返回一个map size图片的大小 mime图片的类型
	 * @exception 
	 * @since  1.0.0
	 */
	public static Map<String, Object> downPicture(String filePath,
			String imageUrl, String fileName) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer size = null;
		String mime = null;
		try {
			File files = new File(filePath);
			if (!files.exists()) {
				files.mkdirs();
			}
			URL url = new URL(imageUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			InputStream is = connection.getInputStream();
			if (!filePath.endsWith("/")) {
				filePath = filePath + "/";
			}
			File file = new File(filePath + fileName);
			FileOutputStream out = new FileOutputStream(file);
			int i = 0;
			while ((i = is.read()) != -1) {
				out.write(i);
			}
			size = connection.getContentLength();
			mime = connection.getContentType();
			is.close();
		} catch (Exception e) {

		}
		map.put("size", size);
		map.put("mime", mime);
		return map;
	}
	/**
	 * 替换中文符号
	 * @param line
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String replace(String line) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("，", ",");
		map.put("。", ".");
		map.put("〈", "<");
		map.put("〉", ">");
		map.put("‖", "|");
		map.put("《", "<");
		map.put("》", ">");
		map.put("〔", "[");
		map.put("〕", "]");
		map.put("﹖", "?");
		map.put("？", "?");
		map.put("“", "\"");
		map.put("”", "\"");
		map.put("：", ":");
		map.put("、", ",");
		map.put("（", "(");
		map.put("）", ")");
		map.put("【", "[");
		map.put("】", "]");
		map.put("—", "-");
		map.put("～", "~");
		map.put("！", "!");
		map.put("‵", "'");
		map.put("①", "1");
		map.put("②", "2");
		map.put("③", "3");
		map.put("④", "4");
		map.put("⑤", "5");
		map.put("⑥", "6");
		map.put("⑦", "7");
		map.put("⑧", "8");
		map.put("⑨", "9");

		int length = line.length();
		for (int i = 0; i < length; i++) {
			String charat = line.substring(i, i + 1);
			if (map.get(charat) != null) {
				line = line.replace(charat, (String) map.get(charat));
			}
		}
		return line;
	}
	public static void main(String[] args) throws Exception {
		/*String type = getFileType("E:\\tomcat-8.5.5\\webapps\\upload\\index.jtpl");
		System.out.println(type);
		System.out.println(getLastFileTime("E:\\tomcat-8.5.5\\webapps\\upload\\index.jtpl"));*/
		//System.out.println(getFileExts("jpg"));
		yaSuoZip("E:\\tomcat-8.5.5\\webapps\\upload\\test", "E:\\tomcat-8.5.5\\webapps\\upload");
		jieYaZip("E:\\tomcat-8.5.5\\webapps\\upload\\test", "E:\\tomcat-8.5.5\\webapps\\upload\\test\\t");
	}
}
