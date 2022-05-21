#!/bin/sh
sudo git config --system receive.denyNonFastForwards true && git remote add bitbucket ssh://git@bitbucket.ase.in.tum.de:7999/eist22t02/eist22t02-scrumdogmillionaires.git && git config --global alias.pushall '!git remote | xargs -L1 git push --all'
