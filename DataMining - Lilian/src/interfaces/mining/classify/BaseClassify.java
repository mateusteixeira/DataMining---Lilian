package interfaces.mining.classify;

import interfaces.Base;

/**
 * object database for classification
 * @author evaristowb
 */

public class BaseClassify {

    /**
     * database for training
     */
    private Base train;

    /**
     * database for test
     */
    private Base test;

    /**
     * returns the database to test
     * @return test - database test
     */
    public Base getTest() {
        return test;
    }

    /**
     * setting the database of test
     * @param test - database test
     */
    public void setTest(Base test) {
        this.test = test;
    }

    /**
     * returns the database to train
     * @return train - database train
     */
    public Base getTrain() {
        return train;
    }

    /**
     * setting the database of train
     * @param train - database train
     */
    public void setTrain(Base train) {
        this.train = train;
    }

}
