package edu.kvcc.cis298.cis298inclass1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheatActivity
		extends AppCompatActivity
{
	// string key for intent, needs to be really long and unique
	private static final String EXTRA_ANSWER_IS_TRUE = "edu.kvcc.cis298.cis298inclass1.answer_is_true";
	private static final String EXTRA_ANSWER_SHOWN = "edu.kvcc.cis298.cis298inclass1.answer_shown";

	private TextView mAnswerTextView;
	private Button mShowAnswerButton;
	private boolean mAnswerIsTrue;

	public static Intent newIntent(Context packageContext, boolean answerIsTrue)
	{
		Intent i = new Intent(packageContext, CheatActivity.class);
		i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
		return i;
	}

	public static boolean wasAnswerShown(Intent result)
	{
		return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

		mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

		mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);

		mShowAnswerButton.setOnClickListener(
			new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int textId;
					if(mAnswerIsTrue)
					{
						textId = R.string.true_button;
					}
					else
					{
						textId = R.string.false_button;
					}
					mAnswerTextView.setText(textId);
					setAnswerShownResult(true);
				}
			}
		);

	}

	private void setAnswerShownResult(boolean isAnswerShown)
	{
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
}
