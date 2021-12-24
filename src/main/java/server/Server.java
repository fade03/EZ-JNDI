package server;

import cli.Config;

public interface Server extends Runnable {
    Server acceptCfg(Config cfg);
}
