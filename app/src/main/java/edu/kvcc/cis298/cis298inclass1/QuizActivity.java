package edu.kvcc.cis298.cis298inclass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity
		extends AppCompatActivity
{
	// string keys for bundle, doesn't need to be really long and unique
	// TODO redo the bundle stuff that was lost
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	private static final int REQUEST_CODE_CHEAT = 0;

	private Button mTrueButton,
						mFalseButton,
						mCheatButton,
						mNextButton;
	private TextView mQuestionTextView;
	private Question[]  mQuestionBank = new Question[]
	{
		new Question(R.string.question_oceans, true),
		new Question(R.string.question_mideast, false),
		new Question(R.string.question_africa, false),
		new Question(R.string.question_americas, true),
		new Question(R.string.question_asia, true)
	};
	private int mCurrentIndex = 0;
	private boolean mIsCheater;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		updateQuestionText();

		mTrueButton = (Button) findViewById(R.id.true_button);
		mFalseButton = (Button) findViewById(R.id.false_button);
		mCheatButton = (Button) findViewById(R.id.cheat_button);
		mNextButton = (Button) findViewById(R.id.next_button);

		mTrueButton.setOnClickListener(
			new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					checkAnswer(true);
				}
			}
		);
		mFalseButton.setOnClickListener(
			new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					checkAnswer(false);
				}
			}
		);
		mCheatButton.setOnClickListener(
				new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
						Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
						startActivityForResult(i, REQUEST_CODE_CHEAT);
					}
				}
		);
		mNextButton.setOnClickListener(
			new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					mCurrentIndex++;
					if(mCurrentIndex == mQuestionBank.length)
					{
						mCurrentIndex = 0;
					}
					mIsCheater = false;
					updateQuestionText();
				}
			}
		);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode != RESULT_OK)
		{
			// don't need to do anything
			return;
		}

		if(requestCode == REQUEST_CODE_CHEAT)
		{
			if(data == null)
			{
				return;
			}

			if(CheatActivity.wasAnswerShown(data))
			{
				mIsCheater = true;
			}
			else
			{
				// do nothing, probably
			}
		}
	}

	private void doCorrectToast()
	{
		Toast.makeText(
			QuizActivity.this,
			R.string.correct_toast,
			Toast.LENGTH_SHORT
		).show();
	}

	private void doIncorrectToast()
	{
		Toast.makeText(
				QuizActivity.this,
				R.string.incorrect_toast,
				Toast.LENGTH_SHORT
		).show();
	}

	private void doJudgementToast()
	{
		Toast.makeText(
				QuizActivity.this,
				R.string.judgement_toast,
				Toast.LENGTH_SHORT
		).show();
	}

	private void checkAnswer(boolean userAnsweredTrue)
	{
		if(mIsCheater)
		{
			doJudgementToast();
		}
		else
		{
			if(userAnsweredTrue == mQuestionBank[mCurrentIndex].isAnswerTrue())
			{
				doCorrectToast();
			}
			else
			{
				doIncorrectToast();
			}
		}
	}

	private void updateQuestionText()
	{
		mQuestionTextView.setText(
				mQuestionBank[mCurrentIndex].getTextResId()
		);
	}
}
