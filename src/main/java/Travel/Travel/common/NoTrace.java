package Travel.Travel.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class NoTrace extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		Throwable thrown = tr.getThrowable();
		StackTraceElement[] outTrace = new StackTraceElement[0];
		thrown.setStackTrace(outTrace);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {

		Throwable thrown = tr.getThrowable();
		StackTraceElement[] outTrace = new StackTraceElement[0];
		thrown.setStackTrace(outTrace);

	}

	@Override
	public void onTestSuccess(ITestResult tr) {

	}

}
