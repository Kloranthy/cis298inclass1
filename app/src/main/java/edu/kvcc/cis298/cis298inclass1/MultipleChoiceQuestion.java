package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by jweigel0843 on 9/26/2016.
 */
public class MultipleChoiceQuestion
{
	private int mQuestionTextResId,	mCorrectChoiceId;
	private int[] mChoiceResIds;

	public MultipleChoiceQuestion(int questionTextResId, int[] choiceResIds, int correctChoiceId)
	{
		mQuestionTextResId = questionTextResId;
		mChoiceResIds = choiceResIds;
		mCorrectChoiceId = correctChoiceId;
	}

	public int getQuestionTextResId()
	{
		return mQuestionTextResId;
	}

	public int[] getChoiceResIds()
	{
		return mChoiceResIds;
	}

	public int getCorrectChoiceId()
	{
		return mCorrectChoiceId;
	}
}
