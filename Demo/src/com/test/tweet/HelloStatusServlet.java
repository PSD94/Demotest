package com.test.tweet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Servlet implementation class Status
 */
class Key {
	ConfigurationBuilder cf = new ConfigurationBuilder();

	void Registretion() {

		cf.setDebugEnabled(true).setOAuthConsumerKey("gmPFPkA2isKVFrRoju4nEbtr2")
				.setOAuthConsumerSecret("3Iz3Lfs16JTN4qMfvbSWItTe3Pmco3x6CDvmf84Ac1dQuWzfgI")
				.setOAuthAccessToken("861899509403574273-305apWdNbmJ0Eb6wBDlHrKuFF0UpCQs")
				.setOAuthAccessTokenSecret("7p9Kf1t1bj7HhCCahTSrVL4P4Lhysx1IZM6UBV8ydY0Sx");
		
	}
	
	

}

@WebServlet(description = "HelloStatusServlet", urlPatterns = { "/HelloStatusServlet" })
public class HelloStatusServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	

	private static final long serialVersionUID = 1L;
	private TwitterStream twitterStream;
	private FilterQuery fq;
    private Key key ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		key = new Key();
		key.Registretion(); // registretion on twitter
		twitterStream = new TwitterStreamFactory(key.cf.build()).getInstance();

		fq = new FilterQuery();
		String keywords[] = { "cricket", "Cricket", "IPL" };
		fq.track(keywords);
	}
	
	@Override
	public void destroy() {
		
		twitterStream.cleanUp();
		twitterStream = null;
		key = null;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloStatusServlet() {
		// super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		StatusListener statusListener = gatherTweetStatus(out);
		twitterStream.addListener(statusListener);
		twitterStream.filter(fq);

	}

	/** This Method is used to gather the Tweet Application Status from Stream base
	 * @param out
	 * @return
	 */
	private StatusListener gatherTweetStatus(PrintWriter out) {
		StatusListener statusListener = new StatusListener() {

			@Override
			public void onException(Exception exception) {
				out.println("Exception : "+exception.getMessage());
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
			}

			@Override
			public void onStatus(Status status) {
				String statusMessage = status.getCreatedAt() + " @" + status.getUser().getScreenName() + " - " + status.getText();
				System.out.println(statusMessage);
				out.println(statusMessage);

			}

			@Override
			public void onStallWarning(StallWarning stallWarning) {
				// TODO Auto-generated method stub
				out.println("stallWarning :: "+stallWarning.getMessage());

			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {

			}
		};
		return statusListener;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
