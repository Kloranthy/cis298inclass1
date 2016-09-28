package edu.kvcc.cis298.cis298inclass1;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * TODO: fix submit to use selected button to get text resource Id and submit that to the check answer
 *
 */
public class QuizActivity
		extends AppCompatActivity
{
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	private Button	mChoice1Button,
					mChoice2Button,
					mChoice3Button,
					mChoice4Button,
					mSubmitButton,
					mNextButton;
	private TextView mQuestionTextView;
	private RadioGroup mQuestionGroup;

	private MultipleChoiceQuestion[]  mQuestionBank = new MultipleChoiceQuestion[]
	{
		new MultipleChoiceQuestion(
				R.string.question_1_multi,
				new int[]
				{
					R.string.question_1_choice_1,
					R.string.question_1_choice_2,
					R.string.question_1_choice_3,
					R.string.question_1_choice_4
				},
				R.id.multiple_choice_button_3),
		new MultipleChoiceQuestion(
				R.string.question_2_multi,
				new int[]
				{
					R.string.question_2_choice_1,
					R.string.question_2_choice_2,
					R.string.question_2_choice_3,
					R.string.question_2_choice_4
				},
				R.id.multiple_choice_button_2)
	};
	private int mCurrentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		Log.d(TAG, "onCreate(Bundle) called");

		if(savedInstanceState != null)
		{
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
		}

		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		mQuestionGroup = (RadioGroup) findViewById(R.id.multiple_group);
		mChoice1Button = (RadioButton) findViewById(R.id.multiple_choice_button_1);
		mChoice2Button = (RadioButton) findViewById(R.id.multiple_choice_button_2);
		mChoice3Button = (RadioButton) findViewById(R.id.multiple_choice_button_3);
		mChoice4Button = (RadioButton) findViewById(R.id.multiple_choice_button_4);
		updateQuestion();
		mSubmitButton = (Button) findViewById(R.id.submit_button);
		mNextButton = (Button) findViewById(R.id.next_button);

		mSubmitButton.setOnClickListener(
			new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					checkAnswer();
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
					mCurrentIndex %= mQuestionBank.length;
					updateQuestion();
				}
			}
		);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();

		Log.d(TAG, "onDestroy() called");
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		Log.d(TAG, "onPause() called");
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		Log.d(TAG, "onResume() called");
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		Log.d(TAG, "onStart() called");
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		Log.d(TAG, "onStop() called");
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		Log.d(TAG, "onSaveInstanceState(Bundle) called");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	private void checkAnswer()
	{
		Log.d(TAG, "checkAnswer() called");

		if(mQuestionBank[mCurrentIndex].getCorrectChoiceId() == mQuestionGroup.getCheckedRadioButtonId())
		{
			Toast.makeText(
					QuizActivity.this,
					R.string.correct_toast,
					Toast.LENGTH_SHORT
			).show();
		}
		else
		{
			Toast.makeText(
					QuizActivity.this,
					R.string.incorrect_toast,
					Toast.LENGTH_SHORT
			).show();
		}
	}

	private void updateQuestion()
	{
		Log.d(TAG, "updateQuestion() called");

		mQuestionGroup.clearCheck();

		mQuestionTextView.setText(
				mQuestionBank[mCurrentIndex].getQuestionTextResId()
		);

		mChoice1Button.setText(
				mQuestionBank[mCurrentIndex].getChoiceResIds()[0]
		);
		mChoice2Button.setText(
				mQuestionBank[mCurrentIndex].getChoiceResIds()[1]
		);
		mChoice3Button.setText(
				mQuestionBank[mCurrentIndex].getChoiceResIds()[2]
		);
		mChoice4Button.setText(
				mQuestionBank[mCurrentIndex].getChoiceResIds()[3]
		);
	}
}
