package com.github.jtreport.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jTreport utils methods.
 * 
 * @author Fabio Rubino.
 * 
 */
public class JTreportUtils {

	private static final Logger L = LoggerFactory
			.getLogger(JTreportUtils.class);

	public static String convertTime(final long millisecond) {
		String timeUnit = " s";

		double secondsDuration = TimeUnit.MILLISECONDS.convert(millisecond,
				TimeUnit.NANOSECONDS) / 1000.0;

		if (secondsDuration >= 60.0) {
			timeUnit = " m";
			secondsDuration = secondsDuration / 60.0;
		}
		if (secondsDuration >= 60.0) {
			timeUnit = " h";
			secondsDuration = secondsDuration / 60.0;
		}

		return secondsDuration + timeUnit;
	}

	public static boolean copyFile(final File toCopy, final File destFile) {
		try {
			return JTreportUtils.copyStream(new FileInputStream(toCopy),
					new FileOutputStream(destFile));
		} catch (final FileNotFoundException e) {
			L.error("File not found [" + destFile + "]", e);
		}
		return false;
	}

	private static boolean copyFilesRecusively(final File toCopy,
			final File destDir) {
		assert destDir.isDirectory();

		if (!toCopy.isDirectory()) {
			return JTreportUtils.copyFile(toCopy,
					new File(destDir, toCopy.getName()));
		} else {
			final File newDestDir = new File(destDir, toCopy.getName());
			if (!newDestDir.exists() && !newDestDir.mkdir()) {
				return false;
			}
			for (final File child : toCopy.listFiles()) {
				if (!JTreportUtils.copyFilesRecusively(child, newDestDir)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean copyJarResourcesRecursively(final File destDir,
			final JarURLConnection jarConnection) throws IOException {

		final JarFile jarFile = jarConnection.getJarFile();

		for (final Enumeration<JarEntry> e = jarFile.entries(); e
				.hasMoreElements();) {
			final JarEntry entry = e.nextElement();
			if (entry.getName().startsWith(jarConnection.getEntryName())) {
				final String filename = StringUtils.removeStart(
						entry.getName(), jarConnection.getEntryName());

				final File f = new File(destDir, filename);
				if (!entry.isDirectory()) {
					final InputStream entryInputStream = jarFile
							.getInputStream(entry);
					if (!JTreportUtils.copyStream(entryInputStream, f)) {
						return false;
					}
					entryInputStream.close();
				} else {
					if (!JTreportUtils.ensureDirectoryExists(f)) {
						final String msgErr = "Could not create directory: "
								+ f.getAbsolutePath();
						L.debug(msgErr);
						throw new IOException(msgErr);
					}
				}
			}
		}
		return true;
	}

	public static boolean copyResourcesRecursively(final URL originUrl,
			final File destination) {
		try {
			final URLConnection urlConnection = originUrl.openConnection();
			if (urlConnection instanceof JarURLConnection) {
				return JTreportUtils.copyJarResourcesRecursively(destination,
						(JarURLConnection) urlConnection);
			} else {
				return JTreportUtils.copyFilesRecusively(
						new File(originUrl.getPath()), destination);
			}
		} catch (final IOException e) {
			L.error(e.getMessage(), e);
		}
		return false;
	}

	private static boolean copyStream(final InputStream is, final File f) {
		try {
			return JTreportUtils.copyStream(is, new FileOutputStream(f));
		} catch (final FileNotFoundException e) {
			L.error("File not found [" + f.getAbsolutePath() + "]", e);
		}
		return false;
	}

	private static boolean copyStream(final InputStream is,
			final OutputStream os) {
		try {
			final byte[] buf = new byte[1024];

			int len = 0;
			while ((len = is.read(buf)) > 0) {
				os.write(buf, 0, len);
			}
			is.close();
			os.close();
			return true;
		} catch (final IOException e) {
			L.error(e.getMessage(), e);
		}
		return false;
	}

	private static boolean ensureDirectoryExists(final File f) {
		return f.exists() || f.mkdir();
	}
}
