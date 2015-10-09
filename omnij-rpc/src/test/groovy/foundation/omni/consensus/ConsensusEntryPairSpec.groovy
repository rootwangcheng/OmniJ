package foundation.omni.consensus

import foundation.omni.net.OmniMainNetParams
import foundation.omni.rpc.BalanceEntry
import org.bitcoinj.core.Address
import spock.lang.Specification


/**
 * ConsensusEntryPair Tests
 */
class ConsensusEntryPairSpec extends Specification {

    static final Address testAddress = OmniMainNetParams.get().exodusAddress // Could be any valid address

    def "is immutable"() {
        setup:
        def pair = new ConsensusEntryPair(testAddress, new BalanceEntry(0,0), new BalanceEntry(0,0))

        when: "we try to change it"
        pair.entry1 = new BalanceEntry(1,1)

        then: "an exception is thrown and value isn't changed"
        ReadOnlyPropertyException e = thrown()
    }

    def "is iterable"() {
        setup:
        def pair = new ConsensusEntryPair(testAddress, new BalanceEntry(0,0), new BalanceEntry(1,1))

        when: "we iterate it"
        def objs = []
        for (item in pair) {
            objs.add(item)
        }

        then: "it worked"
        objs.size() == 3
        objs[0] == testAddress
        objs[1] == new BalanceEntry(0,0)
        objs[2] == new BalanceEntry(1,1)
    }

}
