package gr.iti.mklab.utils;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class URLProcessing {

	private static URLProcessing mInstance = new URLProcessing();

	public static URLProcessing getInstance() {

		if (mInstance == null) {
			mInstance = new URLProcessing();
		}
		return mInstance;

	}

	/**
	 * Checks if a url is a page of Twitter.
	 * 
	 * @param url
	 *            to be checked
	 * @return boolean true if the url is twitter page or false if it is not.
	 */
	private boolean isTwitterPage(String url) {

		return (url.contains("twitter") || url.contains("twitpic.com"));

	}

	private boolean isYouTubePage(String url) {

		return (url.contains("you.tube") || url.contains("youtube"));

	}

	private boolean isFacebookPage(String url) {

		return (url.contains("fb.me") || url.contains("facebook"));

	}

	private boolean isInstagramPage(String url) {

		return (url.contains("instagram"));

	}

	private boolean isFlickrPage(String url) {

		return (url.contains("flickr") || url.contains("flic.kr"));

	}

	private boolean isPinterestPage(String url) {

		return (url.contains("pinterest") || url.contains("flic.kr"));

	}

	public boolean isTumblrPage(String url) {
		
		return (url.matches(".+.tumblr.com") || url.contains("tumblr.com"));

	}

	public boolean isUncompleted(String url) {

		if (url.endsWith("…")) {
			System.out.println("check for " + url);
			return true;
		}

		return false;
	}

	public String processUrlForRunnable(String url) {

		url = url.replaceAll(
				"(http://|https://|http://www\\.|https://www\\.|www\\.)", "");
		String host;
		if (url.contains("/")) {
			host = url.split("/")[0];
		} else {
			host = url;
		}
		if (URLProcessing.getInstance().isTumblrPage(host)) {
			// Tumblr users' pages have the form :
			// "username.tumblr.com".
			// Just remove the username part resulting in "tumblr.com".
			host = host.substring(host.indexOf(".") + 1, host.length());
		}

		return host;
	}
	
	public boolean isAnImage(String url) {
		
		if (url.contains("pbs.twimg.com") || url.contains("p.twimg.com")) return true;
		
		//extra check
		if (url.contains("p.twimg.com")) {
			url = url.substring(0, url.indexOf("p.twimg.com") + 11)
					+ "/media"
					+ url.substring(url.indexOf("p.twimg.com") + 11,
							url.length());
		}
		
		Image image;
		try {
			image = ImageIO.read(new URL(url));
		}
		catch(IOException e) {
			System.out.println("Not an image!");
			return false;
		}
		
		if (image==null) return false;
		
		return true;
		
	}

	public boolean isAppropriateUrl(String longUrl) {

		return (StringProcessing.getInstance().isAppropriate(longUrl)
				&& !isTwitterPage(longUrl) && !isUncompleted(longUrl)
				&& !isFacebookPage(longUrl) && !isYouTubePage(longUrl)
				&& !isTumblrPage(longUrl) && !isInstagramPage(longUrl)
				&& !isFlickrPage(longUrl) && !isPinterestPage(longUrl))
				&& !isAnImage(longUrl);

	}
}
