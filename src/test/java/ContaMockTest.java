import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ContaMock.class)
public class ContaMockTest {

    private double valorDep;
    private double valorMaior;
    private double valorNeg;
    private double valorSac;

    @Before
    public void prepareTests(){
        valorDep = 15;
        valorMaior = 19;
        valorNeg = -15.00;
        valorSac = 8.00;
    }

    @Test
    public void testDepositarValorComSaldoZero() throws Exception {

        //arrange
        ContaMock contaMock =  new ContaMock();
        final String METHOD="getSaldo";

        ContaMock spy = PowerMockito.spy(contaMock);
        PowerMockito.when(spy, METHOD)
                    .thenReturn( (Double) 3000.00);

        double expect = 2020;
        double actual = 0;

        //act
        actual = spy.depositarValor(valorDep);

        //assert
        Assert.assertEquals(expect, actual, 0);
        PowerMockito.verifyPrivate(spy, Mockito
                .times(1))
                .invoke("getSaldo");

    }

    @Test
    public void testSacarValorComSaldoMaiorQueZero() throws Exception {

        //arrange
        ContaMock conta =  new ContaMock();

        double expect = 4.00;
        double actual;

        //act
        conta.depositarValor(valorDep);
        conta.sacarValor(valorSac);

        actual = conta.getSaldo();

        //assert
        Assert.assertEquals(  expect, actual, 0);

    }

    @Test
    public void testDepositoValorMaiorQue10() throws Exception {

        //arrange
        ContaMock conta =  new ContaMock();
        double valorDep2 = 13.00;
        double expect = 4.00;
        double actual;

        //act
        conta.depositarValor(valorDep);
        conta.depositarValor(valorDep2);
        conta.sacarValor(valorSac);

        actual = conta.getSaldo();

        //assert
        Assert.assertEquals(  expect, actual, 0);

    }

    @Test
    public void testDepositarValorComValorNegativo() throws Exception {

        //arrange
        ContaMock conta =  new ContaMock();
        double expect = 12.00;
        double actual;

        //act
        conta.depositarValor(valorDep);
        conta.depositarValor(valorNeg);

        actual = conta.getSaldo();

        //assert
        Assert.assertEquals(  expect, actual, 0);

    }

    @Test
    public void testSacarValorComValorNegativo() throws Exception {

        //arrange
        ContaMock conta =  new ContaMock();
        double expect = 12.00;
        double actual;

        //act
        conta.depositarValor(valorDep);
        conta.sacarValor(valorNeg);

        actual = conta.getSaldo();

        //assert
        Assert.assertEquals(  expect, actual, 0);

    }

}
